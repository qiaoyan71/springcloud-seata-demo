package cn.dmego.seata.tcc.order.controller;

import cn.dmego.seata.common.dto.ReturnResult;
import cn.dmego.seata.tcc.order.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @className: OrderController
 *
 * @description: 订单服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/7 17:27
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/try")
    public ReturnResult<Boolean> orderTry(
                     @RequestParam("orderId") Long orderId,
                     @RequestParam("userId") Long userId,
                     @RequestParam("productId") Long productId,
                     @RequestParam("count") Integer count,
                     @RequestParam("payAmount") Integer payAmount){
        return ReturnResult.success(orderService.orderTry(null, orderId, userId, productId, count, payAmount));
    }

}
