package cn.dmego.seata.xa.business.serivce.impl;


import cn.dmego.seata.xa.business.feign.OrderService;
import cn.dmego.seata.xa.business.feign.ProductService;
import cn.dmego.seata.xa.business.serivce.BusinessService;
import cn.dmego.seata.common.dto.BusinessDTO;
import cn.dmego.seata.common.dto.OrderDTO;
import cn.dmego.seata.common.dto.ProductDTO;
import cn.dmego.seata.common.util.IDUtils;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * BusinessServiceImpl
 *
 * @author qiaoyan
 * @date 2022-11-24 11:09:03
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    @Override
    @GlobalTransactional
    public String handleBusiness(BusinessDTO businessDTO) {

        log.info("[handleBusiness] 开始下单");
        log.info("[handleBusiness] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        boolean reduceStock = productService.reduceStock(new ProductDTO(businessDTO.getProductId(), businessDTO.getCount())).getData();

        // 查询 商品单价
        Integer price = productService.getPrice(businessDTO.getProductId()).getData();
        Integer payAmount = price * businessDTO.getCount();

        // 生成订单 ID
        Long orderId = IDUtils.nextId();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setProductId(businessDTO.getProductId());
        orderDTO.setCount(businessDTO.getCount());
        orderDTO.setPayAmount(payAmount);
        // 创建订单
        boolean createOrder = orderService.createOrder(orderDTO).getData();

        if(!reduceStock || !createOrder){
            throw new RuntimeException("下单失败");
        }

        log.info("[handleBusiness] 下单成功, 订单Id: "+ orderId);
        return "Place Order Success";
    }
}
