package com.miracle.directproducer.config;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.QueueEnum;
import com.miracle.common.api.constant.RoutingKeyConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : sungm
 * @date : 2021-03-30 12:04
 */
@Configuration
public class RabbitConfig {

    /* 说明：定义交换机------------------------------------------------------------------------------------------------- */

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ExchangeEnum.DIRECT_MESSAGE.getName(), ExchangeEnum.DIRECT_MESSAGE.getDurable()
                , ExchangeEnum.DIRECT_MESSAGE.getAutoDelete());
    }

    /* 说明：定义队列--------------------------------------------------------------------------------------------------- */

    @Bean
    public Queue smsQueue() {
        return new Queue(QueueEnum.SMS.getName(), QueueEnum.SMS.getDurable(), QueueEnum.SMS.getExclusive()
                , QueueEnum.SMS.getAutoDelete());
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(QueueEnum.EMAIL.getName(), QueueEnum.EMAIL.getDurable(), QueueEnum.EMAIL.getExclusive()
                , QueueEnum.EMAIL.getAutoDelete());
    }

    /* 说明：队列绑定交换机，队列没有绑定指定的交换机时将绑定默认的交换机--------------------------------------------------------- */

    @Bean
    public Binding smsBinding() {
        //direct模式下需要指定routingKey
        return BindingBuilder.bind(smsQueue()).to(directExchange()).with(RoutingKeyConstant.DIRECT_SMS);
    }

    @Bean
    public Binding emailBinding() {
        //direct模式下需要指定routingKey
        return BindingBuilder.bind(emailQueue()).to(directExchange()).with(RoutingKeyConstant.DIRECT_EMAIL);
    }
}
