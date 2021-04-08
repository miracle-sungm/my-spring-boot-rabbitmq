package com.miracle.commonconsumer.listener;

import com.miracle.common.api.constant.QueueNameConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : sungm
 * @date : 2021-03-30 14:16
 */
@Component
@RabbitListener(queues = {QueueNameConstant.SMS})
public class OrderSmsRabbitListener {

    @RabbitHandler
    public void sendSms(String message) {
        System.out.println("发送短信：" + message);
    }

}
