package cn.dmego.seata.at.product.controller;


import cn.dmego.seata.at.product.service.ProductService;
import cn.dmego.seata.common.dto.ProductDTO;
import cn.dmego.seata.common.dto.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: ProductController
 *
 * @description: ProductController
 * @author: ZengKai<dmeago@gmail.com>
 * @date: 2020/12/8 17:42
 **/
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/getPrice")
    public ReturnResult<Integer> getPrice(@RequestParam("productId") Long productId){
        Integer priceById = productService.getPriceById(productId);
        return ReturnResult.success(priceById);
    }

    @PostMapping("/reduce-stock")
    public ReturnResult<Boolean> reduceStock(@RequestBody ProductDTO productDTO) throws Exception {
        log.info("[reduceStock] 收到减少库存请求, 商品:{}, 数量:{}", productDTO.getProductId(), productDTO.getCount());
        return ReturnResult.success(productService.reduceStock(productDTO.getProductId(), productDTO.getCount()));
    }

}