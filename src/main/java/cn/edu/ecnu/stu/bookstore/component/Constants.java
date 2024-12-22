package cn.edu.ecnu.stu.bookstore.component;

public interface Constants {

    String SYSTEM_ERROR = "500";
    String CLIENT_ERROR = "400";
    String SUCCESS = "200";
    String AUTHENTICATION_ERROR = "401";
    String UNAUTHORIZED_ERROR = "403";

    String REGISTER_ERROR_MESSAGE = "用户名已存在";
    String PARAMETER_ERROR_MESSAGE = "参数非法";
    String SUCCESS_MESSAGE = "ok";
    String AUTHENTICATION_ERROR_MESSAGE = "用户未认证或token过期，请先登录";
    String PASSWORD_ERROR = "用户名不存在或密码错误";
    String USER_ID_ERROR = "用户名不存在";
    String AUTHORITY_ERROR = "用户权限不足";
    String STORE_NON_EXIST_ERROR = "商铺不存在";
    String PICTURE_ERROR = "图片解码失败";
    String BOOK_ERROR = "商铺不存在或书籍不存在";
    String STOCK_LEVEL_ERROR = "库存不足";

    String STORE_ID_ERROR = "商铺ID已存在";

    String ORDER_IS_NULL_ERROR = "订单不能为空";

    String BALANCE_ERROR = "余额不足";

    String URL_PREFIX = "http://127.0.0.1:8080";
    String ORDER_NON_EXIST_ERROR = "订单不存在";
    String ORDER_HAS_PAID_ERROR = "订单已支付";

    String STATUS_ERROR = "订单状态错误";
}
