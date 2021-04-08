package com.miracle.fanoutproducer.config;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.QueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(ExchangeEnum.FANOUT_MESSAGE.getName(), ExchangeEnum.FANOUT_MESSAGE.getDurable()
                , ExchangeEnum.FANOUT_MESSAGE.getAutoDelete());
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
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
