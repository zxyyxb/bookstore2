package cn.edu.ecnu.stu.bookstore.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class NewOrderVO {

    private String storeId;

    private List<Map<String, Object>> books;
}
