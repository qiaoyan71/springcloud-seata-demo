package cn.dmego.seata.tcc.business.feign;

import cn.dmego.seata.common.dto.ReturnResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 订单服务
 *
 * @author qiaoyan
 * @date 2022-11-25 10:41:17
 */
@FeignClient(value = "tcc-order-service")
@RequestMapping("/order")
public interface OrderService {

    @PostMapping("/try")
    ReturnResult<Boolean> orderTry(
            // @RequestBody BusinessActionContext actionContext,
                     @RequestParam("orderId") Long orderId,
                     @RequestParam("userId") Long userId,
                     @RequestParam("productId") Long productId,
                     @RequestParam("count") Integer count,
                     @RequestParam("payAmount") Integer payAmount);

}
