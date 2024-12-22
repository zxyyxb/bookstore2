package cn.edu.ecnu.stu.bookstore.mapper;

import cn.edu.ecnu.stu.bookstore.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {

    int insert(@Param("user") User user);

    int delete(@Param("userId") int userId);

    int checkUserByUsername(@Param("username") String username);

    int deleteByName(@Param("username") String username);

    @Select("select id, username, password, address, phone, balance from t_user where id = #{id}")
    User selectOneById(@Param("id") Integer id);

    @Select("select id, username, password, address, phone, balance from t_user where username = #{username}")
    User selectOneByName(@Param("username") String username);

    @Update("update t_user set password = #{password} where username = #{username}")
    int updatePassword(@Param("username") String username, @Param("password") String password);

    @Update("update t_user set balance = balance - #{price} where id = #{userId}")
    int minusBalance(@Param("userId") int userId, @Param("price") BigDecimal price);

    @Update("update t_user set balance = balance + #{addValue} where id = #{userId}")
    void addBalance(@Param("userId") Integer userId,@Param("addValue") BigDecimal addValue);
}
