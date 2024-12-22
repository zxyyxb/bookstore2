package cn.edu.ecnu.stu.bookstore.controller;

import cn.edu.ecnu.stu.bookstore.component.Result;
import cn.edu.ecnu.stu.bookstore.pojo.vo.AddFundsVO;
import cn.edu.ecnu.stu.bookstore.pojo.vo.NewOrderVO;
import cn.edu.ecnu.stu.bookstore.service.impl.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/new_order")
    public Result newOrder(@RequestBody NewOrderVO newOrderVO) {

            String orderId = buyerService.createOrder(newOrderVO);
            Map<String, String> response = new HashMap<>();
            response.put("order_id", orderId);
            return Result.success(response);
    }



    @PostMapping("/add_funds")
    public ResponseEntity<Map<String, Object>> addFunds(@RequestBody AddFundsVO addFundsVO) {

        buyerService.addFunds(addFundsVO);
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("code", 200);
            put("message", "充值成功");
        }});

    }


    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> makePayment(@RequestBody Map<String, String> map) {
        try {
            // 从 map 中提取参数
            String userId = map.get("userId");
            String orderId = map.get("orderId");
            String password = map.get("password");

            // 检查参数是否完整
            if (userId == null || orderId == null || password == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("code", 400);
                response.put("message", "参数缺失");
                return ResponseEntity.badRequest().body(response);
            }

            // 调用 Service 完成付款逻辑
            buyerService.makePayment(userId, orderId, password);

            // 返回成功响应
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "付款成功");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 401);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}