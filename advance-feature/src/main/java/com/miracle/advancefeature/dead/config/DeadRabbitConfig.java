package com.miracle.advancefeature.dead.config;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.QueueEnum;
import com.miracle.common.api.constant.RoutingKeyConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列配置
 *
 * @author : sungm
 * @date : 2021-03-31 10:36
 */
@Configuration
public class DeadRabbitConfig {

    /* 说明：定义交换机------------------------------------------------------------------------------------------------- */

    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange(ExchangeEnum.DEAD_DIRECT_MESSAGE.getName()
                , ExchangeEnum.DEAD_DIRECT_MESSAGE.getDurable()
                , ExchangeEnum.DEAD_DIRECT_MESSAGE.getAutoDelete());
    }

    /* 说明：定义队列--------------------------------------------------------------------------------------------------- */

    @Bean
    public Queue deadQueue() {
        Map<String, Object> args = new HashMap<>(8);
        return new Queue(QueueEnum.DEAD.getName(), QueueEnum.DEAD.getDurable(), QueueEnum.DEAD.getExclusive()
                , QueueEnum.DEAD.getAutoDelete(), args);
    }

    /* 说明：队列绑定交换机，队列没有绑定指定的交换机时将绑定默认的交换机--------------------------------------------------------- */

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange()).with(RoutingKeyConstant.DIRECT_DEAD);
    }

}
