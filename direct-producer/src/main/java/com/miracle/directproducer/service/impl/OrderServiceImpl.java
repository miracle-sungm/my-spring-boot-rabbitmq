package com.miracle.directproducer.service.impl;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.RoutingKeyConstant;
import com.miracle.directproducer.service.OrderService;
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
        * （1）direct模式下需要指定routingKey，并且routingKey不能为null
        * （2）direct是一种直发模式，消息通过指定的交换机和routingKey传递到指定的队列。
        * （3）当不同的队列绑定交换机时指定相同的routingKey，那么消息通过交换机和routingKey可以传递到不同的队列，
        *  但不建议这么做，因为可以使用fanout类型的交换机替代。
        */
        //发送短信
        rabbitTemplate.convertAndSend(ExchangeEnum.DIRECT_MESSAGE.getName()
                , RoutingKeyConstant.DIRECT_SMS, message);
        //发送邮件
        rabbitTemplate.convertAndSend(ExchangeEnum.DIRECT_MESSAGE.getName()
                , RoutingKeyConstant.DIRECT_EMAIL, message);
    }

}
