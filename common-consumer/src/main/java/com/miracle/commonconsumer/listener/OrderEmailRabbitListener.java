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
@RabbitListener(queues = {QueueNameConstant.EMAIL})
public class OrderEmailRabbitListener {

    @RabbitHandler
    public void sendEmail(String message) {
        System.out.println("发送邮件：" + message);
    }

}
