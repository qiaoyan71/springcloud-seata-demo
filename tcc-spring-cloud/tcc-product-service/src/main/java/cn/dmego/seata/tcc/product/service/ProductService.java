package cn.dmego.seata.tcc.product.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 产品服务
 *
 * @author qiaoyan
 * @date 2022-11-24 15:59:56
 */
@LocalTCC
public interface ProductService {

    @TwoPhaseBusinessAction(
            name = "productService",            // TCC bean name, must be unique
            commitMethod = "productConfirm",    // commit method name
            rollbackMethod = "productCancel",   // rollback method name
            useTCCFence = true                  // 开启后自动处理 幂等，悬挂 |whether use TCC fence (idempotent,non_rollback,suspend)
    )
    boolean productTry(BusinessActionContext actionContext,
                       @BusinessActionContextParameter(paramName = "productId") Long productId,
                       @BusinessActionContextParameter(paramName = "count") Integer count);

    boolean productConfirm(BusinessActionContext actionContext);

    boolean productCancel(BusinessActionContext actionContext);

    Integer getPriceById(Long productId);
}
