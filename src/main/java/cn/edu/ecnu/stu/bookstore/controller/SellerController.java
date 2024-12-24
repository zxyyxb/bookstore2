package cn.edu.ecnu.stu.bookstore.controller;

import cn.edu.ecnu.stu.bookstore.exception.StoreAlreadyExistsException;
import cn.edu.ecnu.stu.bookstore.service.impl.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerservice;

    @PostMapping("/create_store")
    public ResponseEntity<?> createStore(@RequestBody Map<String, String> body) {
        try {
            String userId = body.get("userId");
            String storeId = body.get("storeId");
            System.out.println("Controller received userId: " + userId + ", storeId: " + storeId);

            // 调用Service处理业务逻辑
            sellerservice.createStore(userId, storeId);

            return ResponseEntity.ok("创建商铺成功");
        } catch (StoreAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("商铺ID已存在");
        }
    }


    @PostMapping("/add_book")
    public ResponseEntity<?> addBook(@RequestBody Map<String, Object> body) {

            // 获取基本参数
            String userId = (String) body.get("userId");
            String storeId = (String) body.get("storeId");
            Integer stockLevel = (Integer) body.get("stockLevel");
            // 获取书籍详细信息
            Map<String, Object> bookInfo = (Map<String, Object>) body.get("bookInfo");


            // 调用 Service 处理
            sellerservice.addBookWithStock(userId, storeId, bookInfo, stockLevel);

            return ResponseEntity.ok("添加图书信息成功");

    }


    @PostMapping("/add_stock_level")
    public ResponseEntity<?> addStookLevel(@RequestBody Map<String, Object> body) {

        // 获取基本参数
        String bookId = (String) body.get("bookId");
        String storeId = (String) body.get("storeId");
        Integer stockLevel = (Integer) body.get("addStockLevel");
        // 获取书籍详细信息


        // 调用 Service 处理
        sellerservice.addStockLevel(storeId, bookId, stockLevel);

        return ResponseEntity.ok("添加图书信息成功");

    }
}
