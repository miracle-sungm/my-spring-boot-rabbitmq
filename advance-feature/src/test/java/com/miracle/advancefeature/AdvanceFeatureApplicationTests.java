package com.miracle.advancefeature;

import com.miracle.advancefeature.dt.service.OrderService;
import com.miracle.advancefeature.ttl.service.TtlProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AdvanceFeatureApplicationTests {

    @Resource
    private TtlProducerService ttlProducerService;
    @Resource
    private OrderService orderService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        ttlProducerService.sendMessage("测试");
    }

    @Test
    void test() {
        for (int i = 0; i < 10; i++) {
            ttlProducerService.sendMessage("测试");
        }
    }

    @Test
    void test1() {
        orderService.generateOrder("test");
    }

    @Test
    void test2() {
        rabbitTemplate.convertAndSend("dt_direct_exchange", "dt"
                , "test");
    }

}
