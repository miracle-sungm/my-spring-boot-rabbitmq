package com.miracle.advancefeature.dt.config;

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
 * 分布式事务：RabbitMQ配置
 *
 * @author : sungm
 * @date : 2021-03-31 16:15
 */
@Configuration
public class DistributedTransactionRabbitMqConfig {

    /* 说明：定义交换机------------------------------------------------------------------------------------------------- */

    @Bean
    public DirectExchange dtDirectExchange() {
        return new DirectExchange(ExchangeEnum.DT_DIRECT_MESSAGE.getName()
                , ExchangeEnum.DT_DIRECT_MESSAGE.getDurable()
                , ExchangeEnum.DT_DIRECT_MESSAGE.getAutoDelete());
    }

    /* 说明：定义队列--------------------------------------------------------------------------------------------------- */

    @Bean
    public Queue dtQueue() {
        Map<String, Object> args = new HashMap<>(4);
        //设置死信队列的交换机
        args.put("x-dead-letter-exchange", ExchangeEnum.DT_DIRECT_MESSAGE.getName());
        //设置死信队列的routingKey
        args.put("x-dead-letter-routing-key", RoutingKeyConstant.DIRECT_DT);
        return new Queue(QueueEnum.DT.getName(), QueueEnum.DT.getDurable(), QueueEnum.DT.getExclusive()
                , QueueEnum.DT.getAutoDelete(), args);
    }

    /* 说明：队列绑定交换机，队列没有绑定指定的交换机时将绑定默认的交换机--------------------------------------------------------- */

    @Bean
    public Binding dtBinding() {
        return BindingBuilder.bind(dtQueue()).to(dtDirectExchange()).with(RoutingKeyConstant.DIRECT_DT);
    }

}
