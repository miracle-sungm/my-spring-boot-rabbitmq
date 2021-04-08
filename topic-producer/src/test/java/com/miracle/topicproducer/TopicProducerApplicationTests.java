package com.miracle.topicproducer;

import com.miracle.topicproducer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class TopicProducerApplicationTests {

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.sendMessage(UUID.randomUUID().toString());
    }

}
