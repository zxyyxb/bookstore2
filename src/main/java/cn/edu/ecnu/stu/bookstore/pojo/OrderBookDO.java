package cn.edu.ecnu.stu.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBookDO {

    private String orderId;

    private String bookId;

    private Integer count;

    private BigDecimal singlePrice;
}
