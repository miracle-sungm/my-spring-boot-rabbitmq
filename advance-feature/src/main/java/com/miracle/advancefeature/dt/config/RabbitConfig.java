package com.miracle.advancefeature.dt.config;

import com.alibaba.fastjson.JSONObject;
import com.miracle.advancefeature.dt.entity.OrderQueue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author : sungm
 * @date : 2021-04-01 11:31
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer, ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        //设置强制：生产者发送消失失败时，会先调用ReturnCallback，再调用ConfirmCallback
        template.setMandatory(Boolean.TRUE);
        template.setReturnsCallback(returned -> {

        });
        template.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (ack) {
                //消息发送成功，消息移历史
                String json = new String(correlationData.getReturned().getMessage().getBody(), StandardCharsets.UTF_8);
                System.out.println(json);
            } else  {
                //消息发送失败，修改持久化消息的状态，启动重试机制。
            }
        });
        configurer.configure(template, connectionFactory);
        return template;
    }

}
