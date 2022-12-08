package cn.dmego.seata.tcc.business.service.impl;

import cn.dmego.seata.common.dto.BusinessDTO;
import cn.dmego.seata.tcc.business.feign.OrderService;
import cn.dmego.seata.tcc.business.feign.ProductService;
import cn.dmego.seata.tcc.business.service.BusinessService;
import cn.dmego.seata.common.util.IDUtils;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className: BusinessServiceImpl
 *
 * @description: 业务服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/6 17:26
 **/
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    @Override
    @GlobalTransactional
    public String handleBusiness(BusinessDTO businessDTO) {
        String xid = RootContext.getXID();
        logger.info("[handleBusiness] 开始下单");
        logger.info("[handleBusiness] 当前 XID: {}",xid);
//        BusinessActionContext actionContext = new BusinessActionContext();
//        actionContext.setXid(null);

        // 扣减库存 Try
        boolean result = productService.productTry(businessDTO.getProductId(), businessDTO.getCount()).getData();
        if(!result){
            throw new RuntimeException("扣减库存一阶段失败");
        }

        // 查询 商品单价
        Integer price = productService.getPrice(businessDTO.getProductId()).getData();
        Integer payAmount = price * businessDTO.getCount();

        // 生成orderId
        Long orderId = IDUtils.nextId();

        // 创建订单 Try
        result = orderService.orderTry(
                    orderId, businessDTO.getUserId(), businessDTO.getProductId(),
                    businessDTO.getCount(), payAmount
                ).getData();
        if(!result){
            throw new RuntimeException("创建订单一阶段失败");
        }
        logger.info("[handleBusiness] 下单成功, 订单Id: " + orderId);
        return "Place Order Success";
    }
}
