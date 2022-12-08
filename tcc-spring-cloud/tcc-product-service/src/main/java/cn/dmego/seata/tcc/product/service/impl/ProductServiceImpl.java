package cn.dmego.seata.tcc.product.service.impl;

import cn.dmego.seata.common.util.ResultHolder;
import cn.dmego.seata.tcc.product.dao.ProductDao;
import cn.dmego.seata.tcc.product.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 仓库服务
 *
 * @author qiaoyan
 * @date 2022-11-24 15:56:51
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean productTry(BusinessActionContext actionContext, Long productId, Integer count) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        log.info("[productTry]: 当前 XID:{}, branchId:{}, 商品:{}， 数量:{}", xId, branchId, productId, count);
        // 锁库存
        int flag = productDao.productTry(productId, count);

        if(flag == 0){
            throw new RuntimeException("库存服务 Try 阶段失败.");
        }

        //事务成功，保存一个标识，供第二阶段进行判断
        ResultHolder.setResult(getClass(), actionContext.getXid(), "p");

        log.info("[productTry]: 冻结 {} 库存成功", count);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean productConfirm(BusinessActionContext actionContext) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        Integer productId = (Integer) actionContext.getActionContext("productId");
        Integer count = ((Integer) actionContext.getActionContext("count"));
        log.info("[productConfirm]: 当前 XID:{}, branchId:{}, 商品:{}， 数量:{}", xId, branchId, productId, count);

        // 幂等控制，如果commit阶段重复执行则直接返回
        if (ResultHolder.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }

        int flag = productDao.productConfirm(productId.longValue(), count);
        if(flag == 0){
            throw new RuntimeException("库存服务 Confirm 阶段失败.");
        }
        // commit成功删除标识
        ResultHolder.removeResult(getClass(), actionContext.getXid());
        log.info("[productConfirm]: 扣除 {} 库存成功", count);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean productCancel(BusinessActionContext actionContext) {
        String xId = actionContext.getXid();
        long branchId = actionContext.getBranchId();
        Integer productId = ((Integer) actionContext.getActionContext("productId"));
        Integer count = ((Integer) actionContext.getActionContext("count"));
        log.info("[productCancel]: 当前 XID:{}, branchId:{}, 商品:{}， 数量:{}", xId, branchId, productId, count);
        // 幂等控制，如果 cancel 阶段重复执行则直接返回
        if (ResultHolder.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }

        int flag = productDao.productCancel(productId.longValue(), count);
        if(flag == 0){
            throw new RuntimeException("库存服务 Cancel 阶段失败.");
        }
        // cancel 成功删除标识
        ResultHolder.removeResult(getClass(), actionContext.getXid());
        log.info("[productCancel]: 解除冻结 {} 库存成功", count);
        return true;
    }

    @Override
    public Integer getPriceById(Long productId) {
        return productDao.selectPriceById(productId);
    }
}
