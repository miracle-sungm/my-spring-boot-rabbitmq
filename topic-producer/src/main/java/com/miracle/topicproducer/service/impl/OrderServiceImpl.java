package com.miracle.topicproducer.service.impl;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.topicproducer.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : sungm
 * @date : 2021-03-30 12:01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param orderId    订单ID
     */
    @Override
    public void sendMessage(String orderId) {
        String message = "订单ID: " + orderId;
        /*
        * 说明：
        * （1）topic是基于direct扩展的，direct只能向指定的routingKey发送消息，topic可以通过表达式（*或#）向满足条件的routingKey发送消息。
        * （2）* 表示必须存在一个匹配项； # 表示可以存在0个、1个或多个匹配项。
        */
        //我们自定义的sms_queue（短信队列）绑定了message_topic_exchange交换机，并指定路由key为：*.order.sms
        //这里向指定的交换机(交换机名称为：message_topic_exchange)和routingKey(routingKey的值为：topic.order.sms)传递消息时，
        //由于 topic.order.sms 满足 *.order.sms(*匹配一个参数) 的条件，因此下面这条指令会将消息添加到sms_queue队列中。
        rabbitTemplate.convertAndSend(ExchangeEnum.TOPIC_MESSAGE.getName()
                , "topic.order.sms", message);

        //我们自定义的email_queue（短信队列）绑定了message_topic_exchange交换机，并指定路由key为：#.email
        //这里向指定的交换机(交换机名称为：message_topic_exchange)和routingKey(routingKey的值为：topic.order.email)传递消息时，
        //由于 topic.order.email 满足 #.email(#匹配0个、一个或多个参数) 的条件，因此下面这条指令会将消息添加到email_queue队列中。
        rabbitTemplate.convertAndSend(ExchangeEnum.TOPIC_MESSAGE.getName()
                , "topic.order.email", message);
    }

}
