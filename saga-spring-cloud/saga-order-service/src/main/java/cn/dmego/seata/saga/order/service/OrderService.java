package cn.dmego.seata.saga.order.service;


/**
 * OrderService
 *
 * @author dmego
 * @date 2021/3/31 10:52
 */
public interface OrderService {

    Boolean createOrder(Long orderId, Long userId, Long productId, Integer amount, Integer count);

    Boolean revokeOrder(Long orderId);

}
