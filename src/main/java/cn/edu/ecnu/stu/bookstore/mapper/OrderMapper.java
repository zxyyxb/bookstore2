package cn.edu.ecnu.stu.bookstore.mapper;

import cn.edu.ecnu.stu.bookstore.pojo.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    // 根据订单ID查询订单信息
    @Select("SELECT * FROM orders WHERE order_id = #{orderId}")
    Order getOrderById(@Param("orderId") String orderId);

    // 更新订单状态
    @Update("UPDATE orders SET status = #{status} WHERE order_id = #{orderId}")
    int updateOrderStatus(@Param("orderId") String orderId, @Param("status") int status);
}