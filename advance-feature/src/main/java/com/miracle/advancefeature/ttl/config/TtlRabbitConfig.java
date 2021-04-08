package com.miracle.advancefeature.ttl.config;

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
 * @author : sungm
 * @date : 2021-03-31 10:36
 */
@Configuration
public class TtlRabbitConfig {

    /* 说明：定义交换机------------------------------------------------------------------------------------------------- */

    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange(ExchangeEnum.TTL_DIRECT_MESSAGE.getName()
                , ExchangeEnum.TTL_DIRECT_MESSAGE.getDurable()
                , ExchangeEnum.TTL_DIRECT_MESSAGE.getAutoDelete());
    }

    /* 说明：定义队列--------------------------------------------------------------------------------------------------- */

    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>(8);
        //设置消息过期时间，单位毫秒
        args.put("x-message-ttl", 10000);
        //设置队列的过期时间，过期自动删除队列，单位毫秒
        args.put("x-expires", 15000);
        //设置就绪状态的最大消息数，超过这个数量被移入死信队列或触发相应的x-overflow。
        args.put("x-max-length", 5);
        //当队列就绪状态的消息达到设置的最大值时，出发拒绝发布的策略（reject-publish），还有其他两种策略：drop-head,reject-publish-dlx
        args.put("x-overflow", "reject-publish");
        //设置死信队列的交换机下·
        args.put("x-dead-letter-exchange", ExchangeEnum.DEAD_DIRECT_MESSAGE.getName());
        //设置死信队列的routingKey
        args.put("x-dead-letter-routing-key", RoutingKeyConstant.DIRECT_DEAD);
        return new Queue(QueueEnum.TTL.getName(), QueueEnum.TTL.getDurable(), QueueEnum.TTL.getExclusive()
                , QueueEnum.TTL.getAutoDelete(), args);
    }

    /* 说明：队列绑定交换机，队列没有绑定指定的交换机时将绑定默认的交换机--------------------------------------------------------- */

    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with(RoutingKeyConstant.DIRECT_TTL);
    }

}
