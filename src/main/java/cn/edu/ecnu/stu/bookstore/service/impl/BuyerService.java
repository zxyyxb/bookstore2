package cn.edu.ecnu.stu.bookstore.service.impl;

import cn.edu.ecnu.stu.bookstore.mapper.BuyerMapper;
import cn.edu.ecnu.stu.bookstore.mapper.OrderMapper;
import cn.edu.ecnu.stu.bookstore.pojo.Order;
import cn.edu.ecnu.stu.bookstore.pojo.User;
import cn.edu.ecnu.stu.bookstore.pojo.vo.AddFundsVO;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cn.edu.ecnu.stu.bookstore.pojo.vo.NewOrderVO;

import java.util.Map;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private OrderMapper orderMapper;
    public String createOrder(NewOrderVO newOrderVO) {
        if (newOrderVO.getStoreId() == null || newOrderVO.getBooks() == null || newOrderVO.getBooks().isEmpty()) {
            throw new IllegalArgumentException("参数缺失或无效");
        }

        for (Map<String, Object> book : newOrderVO.getBooks()) {
            String bookId = (String) book.get("id");
            Integer count = (Integer) book.get("count");

            if (bookId == null || count == null || count <= 0) {
                throw new IllegalArgumentException("书籍参数缺失或无效");
            }

            // 调用数据库验证图书是否存在
            if (!buyerMapper.isBookExist(bookId)) {
                throw new IllegalArgumentException("书籍不存在，ID: " + bookId);
            }
        }
        // 生成订单ID (UUID)
        String orderId = UUID.randomUUID().toString();

        // 插入订单到数据库
        buyerMapper.insertOrder(orderId, newOrderVO.getStoreId());

        // 插入每本书的购买记录
        for (Map<String, Object> book : newOrderVO.getBooks()) {
            String bookId = (String) book.get("id");
            Integer count = (Integer) book.get("count");

            if (bookId == null || count == null || count <= 0) {
                throw new IllegalArgumentException("书籍ID或数量无效");
            }
            buyerMapper.insertOrderItem(orderId, bookId, count);
        }

        return orderId;
    }

    public void addFunds(AddFundsVO addFundsVO) {
        // 1. 校验用户是否存在
        User user = buyerMapper.getUserById(addFundsVO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        // 2. 验证密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(addFundsVO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }

        // 3. 验证充值金额是否有效
        if (addFundsVO.getAddValue() == null || addFundsVO.getAddValue() <= 0) {
            throw new IllegalArgumentException("充值金额无效");
        }

        // 4. 更新余额
        int rows = buyerMapper.updateBalance(addFundsVO.getUserId(), addFundsVO.getAddValue());
        if (rows != 1) {
            throw new RuntimeException("充值失败");
        }
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void makePayment(String userId, String orderId, String password) {
        // 1. 验证用户是否存在
        User user = buyerMapper.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        // 2. 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }

        // 3. 验证订单是否存在

        Order order = orderMapper.getOrderById(orderId);
        if (order == null ) {
            throw new IllegalArgumentException("订单不存在");
        }

        // 4. 检查用户余额是否足够
        double orderAmount = order.getAmount();
        if (user.getBalance() < orderAmount) {
            throw new RuntimeException("账户余额不足");
        }

        // 5. 扣除用户余额
        int updateUserBalance = buyerMapper.updateBalance(userId, -orderAmount);
        if (updateUserBalance != 1) {
            throw new RuntimeException("扣除余额失败");
        }

/*        // 6. 更新订单状态为已支付
        int updateOrderStatus = orderMapper.updateOrderStatus(orderId, 1);
        if (updateOrderStatus != 1) {
            throw new RuntimeException("更新订单状态失败");
        }*/
    }
}
