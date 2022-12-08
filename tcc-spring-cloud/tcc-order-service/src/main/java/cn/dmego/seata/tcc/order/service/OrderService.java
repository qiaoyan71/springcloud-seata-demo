package cn.dmego.seata.tcc.order.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;


/**
 * 订单服务
 *
 * @author qiaoyan
 * @date 2022-11-24 16:06:47
 */
@LocalTCC
public interface OrderService {

    @TwoPhaseBusinessAction(name = "orderService", commitMethod = "orderConfirm", rollbackMethod = "orderCancel",useTCCFence = true)
    boolean orderTry(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "orderId") Long orderId,
                     @BusinessActionContextParameter(paramName = "userId") Long userId,
                     @BusinessActionContextParameter(paramName = "productId") Long productId,
                     @BusinessActionContextParameter(paramName = "count") Integer count,
                     @BusinessActionContextParameter(paramName = "payAmount") Integer payAmount);

    boolean orderConfirm(BusinessActionContext actionContext);

    boolean orderCancel(BusinessActionContext actionContext);
}
