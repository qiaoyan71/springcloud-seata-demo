package cn.dmego.seata.saga.order.dao;

import cn.dmego.seata.common.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * mapper
 *
 * @author qiaoyan
 * @date 2022-12-05 15:25:31
 */
@Mapper
@Repository
public interface OrderDao {

    @Insert("insert into orders (id, user_id, product_id, count, pay_amount, status) values (#{id}, #{userId}, #{productId}, #{count}, #{payAmount}, 1)")
    int createOrder(OrderDTO order);

    @Update("update orders set status = -1 where id = #{orderId}")
    int revokeOrder(@Param("orderId") Long orderId);
}
