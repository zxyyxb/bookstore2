package cn.edu.ecnu.stu.bookstore.handler;

import cn.edu.ecnu.stu.bookstore.pojo.OrderStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusTypeHandler implements TypeHandler<OrderStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, OrderStatus status, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, status.getValue());
    }

    @Override
    public OrderStatus getResult(ResultSet resultSet, String s) throws SQLException {
        return OrderStatus.getByValue(resultSet.getInt(s));
    }

    @Override
    public OrderStatus getResult(ResultSet resultSet, int i) throws SQLException {
        return OrderStatus.getByValue(resultSet.getInt(i));
    }

    @Override
    public OrderStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        return OrderStatus.getByValue(callableStatement.getInt(i));
    }
}
