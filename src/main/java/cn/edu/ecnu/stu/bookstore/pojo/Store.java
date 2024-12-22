package cn.edu.ecnu.stu.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Store {

    private String storeId;

    private int sellerId;

    private Date createTime;
}
