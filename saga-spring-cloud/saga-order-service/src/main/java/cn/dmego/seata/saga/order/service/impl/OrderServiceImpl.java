package cn.dmego.seata.saga.order.service.impl;

import cn.dmego.seata.common.dto.OrderDTO;
import cn.dmego.seata.saga.order.dao.OrderDao;
import cn.dmego.seata.saga.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * OrderServiceImpl
 *
 * @author dmego
 * @date 2021/3/31 10:51
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createOrder(Long orderId, Long userId, Long productId, Integer amount, Integer count){
        OrderDTO orderDTO = new OrderDTO(orderId, userId, productId, count, amount);
        log.info("[createOrder] 开始创建订单: {}", orderDTO);

        int result = orderDao.createOrder(orderDTO);

        if(result == 0){
            log.warn("[createOrder] 创建订单 {} 失败", orderDTO);
            return false;
        }
        log.info("[createOrder] 保存订单成功: {}", orderDTO.getId());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean revokeOrder(Long orderId){
        log.info("[revokeOrder] 开始撤销订单, orderId: {}", orderId);

        int result = orderDao.revokeOrder(orderId);
        if(result == 0){
            log.warn("[revokeOrder] 撤销订单 {} 失败",orderId);
            return false;
        }

        log.info("[revokeOrder] 撤销订单成功: {}", orderId);
        return true;

    }
}
