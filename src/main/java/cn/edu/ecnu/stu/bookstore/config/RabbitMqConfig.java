package cn.edu.ecnu.stu.bookstore.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    public static final String EXPIRED_ORDER_EXCHANGE = "exchange.expire.order";

    public static final String EXPIRED_ORDER_QUEUE = "queue.expire.order";

    public static final String EXPIRED_ORDER_ROUTING_KEY = "routingKey.expire.order";

    public static final int ORDER_EXPIRE_TIME = 1000 * 60 * 15;

    @Bean
    public Queue expiredOrderQueue() {
        return new Queue(EXPIRED_ORDER_QUEUE, true);
    }

    @Bean
    public CustomExchange expiredOrderExchange() {
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange(EXPIRED_ORDER_EXCHANGE, "x-delayed-message", true, false, map);
    }

    @Bean
    public Binding expiredOrderBinding() {
        return BindingBuilder.bind(expiredOrderQueue()).to(expiredOrderExchange()).with(EXPIRED_ORDER_ROUTING_KEY).noargs();
    }
}
