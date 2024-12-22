package cn.edu.ecnu.stu.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private String orderId;

    private Integer buyerId;

    private String fromAddress;

    private String toAddress;

    private BigDecimal price;

    private OrderStatus status;

    private String storeId;

    private Date createTime;
}
