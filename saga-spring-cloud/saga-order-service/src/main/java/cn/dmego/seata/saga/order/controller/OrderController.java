package cn.dmego.seata.saga.order.controller;

import cn.dmego.seata.saga.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * OrderController
 *
 * @author qiaoyan
 * @date 2022-12-05 15:23:47
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public Boolean createOrder(@RequestParam("orderId") Long orderId,
                               @RequestParam("userId") Long userId,
                               @RequestParam("productId") Long productId,
                               @RequestParam("amount") Integer amount,
                               @RequestParam("count") Integer count){

        return orderService.createOrder(orderId, userId, productId, amount, count);
    }

    @RequestMapping("/revokeOrder")
    public Boolean revokeOrder(@RequestParam("orderId") Long orderId){
        return orderService.revokeOrder(orderId);
    }

}
