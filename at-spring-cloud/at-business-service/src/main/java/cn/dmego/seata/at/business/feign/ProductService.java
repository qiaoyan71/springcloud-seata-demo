package cn.dmego.seata.at.business.feign;

import cn.dmego.seata.common.dto.ProductDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * at-product-service 服务的 Feign 客户端
 *
 * @author qiaoyan
 * @date 2022-11-24 11:06:47
 */
@FeignClient("at-product-service")
@RequestMapping("/product")
public interface ProductService {

    @PostMapping("/reduce-stock")
    ReturnResult<Boolean> reduceStock (@RequestBody ProductDTO productDTO);

    @GetMapping("/getPrice")
    ReturnResult<Integer> getPrice(@RequestParam("productId") Long productId);

}
