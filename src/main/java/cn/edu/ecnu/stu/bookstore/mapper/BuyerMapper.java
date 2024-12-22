package cn.edu.ecnu.stu.bookstore.mapper;

import cn.edu.ecnu.stu.bookstore.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BuyerMapper {

    // 插入订单记录
    @Insert("INSERT INTO orders (order_id, store_id) VALUES (#{orderId}, #{storeId})")
    void insertOrder(@Param("orderId") String orderId, @Param("storeId") String storeId);

    // 插入订单书籍明细记录
    @Insert("INSERT INTO order_items (order_id, book_id, count) VALUES (#{orderId}, #{bookId}, #{count})")
    void insertOrderItem(@Param("orderId") String orderId, @Param("bookId") String bookId, @Param("count") Integer count);

    @Select("SELECT COUNT(1) FROM t_book WHERE id = #{bookId}")
    boolean isBookExist(@Param("bookId") String bookId);

    // 根据用户ID查询用户信息
    @Select("SELECT * FROM t_user WHERE id = #{userId}")
    User getUserById(@Param("userId") String userId);

    // 更新用户余额
    @Update("UPDATE t_user SET balance = balance + #{addValue} WHERE id = #{userId}")
    int updateBalance(@Param("userId") String userId, @Param("addValue") double addValue);
}
