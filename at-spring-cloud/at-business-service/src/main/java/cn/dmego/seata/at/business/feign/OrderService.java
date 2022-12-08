package cn.dmego.seata.at.business.feign;

import cn.dmego.seata.common.dto.OrderDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * at-order-service 服务的 Feign 客户端
 *
 * @author qiaoyan
 * @date 2022-11-24 11:06:30
 */
@FeignClient("at-order-service")
@RequestMapping("/order")
public interface OrderService {

    @PostMapping("/create-order")
    ReturnResult<Boolean> createOrder(@RequestBody OrderDTO orderDTO);

}
