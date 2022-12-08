package cn.dmego.seata.tcc.product.controller;

import cn.dmego.seata.common.dto.ReturnResult;
import cn.dmego.seata.tcc.product.service.ProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @className: ProductController
 *
 * @description: 仓库服务
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/8 17:29
 **/
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/getPrice")
    public ReturnResult<Integer> getPrice(@RequestParam("productId") Long productId){
        return ReturnResult.success(productService.getPriceById(productId));
    }

    @PostMapping("/try")
    public ReturnResult<Boolean> productTry(
                              @RequestParam("productId") Long productId,
                              @RequestParam("count") Integer count){
        return ReturnResult.success(productService.productTry(null, productId, count));
    }

/*    @PostMapping("/confirm")
    public boolean productConfirm(@RequestBody BusinessActionContext actionContext){
        return productService.productConfirm(actionContext);
    }

    @PostMapping("/cancel")
    public boolean productCancel(@RequestBody BusinessActionContext actionContext){
        return productService.productCancel(actionContext);
    }*/

}
