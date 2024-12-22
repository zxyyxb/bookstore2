package cn.edu.ecnu.stu.bookstore.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddFundsVO {
    private String userId; // 用户ID
    private String password; // 用户密码
    private Integer addValue; // 充值金额
}