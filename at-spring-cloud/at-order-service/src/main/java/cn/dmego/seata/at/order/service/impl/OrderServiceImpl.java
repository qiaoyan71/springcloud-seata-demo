package cn.dmego.seata.at.order.service.impl;


import cn.dmego.seata.at.order.dao.OrderDao;
import cn.dmego.seata.at.order.feign.AccountService;
import cn.dmego.seata.at.order.service.OrderService;
import cn.dmego.seata.common.dto.AccountDTO;
import cn.dmego.seata.common.dto.OrderDTO;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单服务
 *
 * @author qiaoyan
 * @date 2022-11-24 15:36:10
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Override
    public boolean createOrder(OrderDTO orderDTO) throws Exception {
        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减余额
        boolean reduceBalance = accountService.reduceBalance(new AccountDTO(orderDTO.getUserId(), orderDTO.getPayAmount())).getData();

        if(!reduceBalance){
            throw new RuntimeException("扣减余额失败");
        }

        // 保存订单
        log.info("[createOrder] 开始创建订单: {}", orderDTO);
        int saveOrder = orderDao.saveOrder(orderDTO);
        if(saveOrder == 0){
            log.warn("[createOrder] 创建订单 {} 失败", orderDTO);
            throw new Exception("创建订单失败");
        }
        log.info("[createOrder] 保存订单: {}", orderDTO.getId());

        return true;
    }

}
