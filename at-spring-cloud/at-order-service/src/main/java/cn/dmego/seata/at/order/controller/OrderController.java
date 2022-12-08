package cn.dmego.seata.at.order.controller;


import cn.dmego.seata.at.order.service.OrderService;
import cn.dmego.seata.common.dto.OrderDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 顺序控制器
 *
 * @author qiaoyan
 * @date 2022-11-24 15:32:38
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create-order")
    public ReturnResult<Boolean> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        log.info("[createOrder] 收到下单请求, 用户:{}, 商品:{}, 数量:{}", orderDTO.getUserId(), orderDTO.getProductId(), orderDTO.getCount());
        return ReturnResult.success(orderService.createOrder(orderDTO));
    }

}
