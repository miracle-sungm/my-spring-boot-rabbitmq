package com.miracle.fanoutproducer.service.impl;

import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.fanoutproducer.service.OrderService;
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
        * （1）fanout是一种分发机制，在这种模式下不需要指定routingKey。
        * （2）rabbitTemplate底层代码会判断当传入的routingKey为null时，设置routingKey默认值为""，所里这里routingKey可以传null或者""。
        * （3）我们在配置中给message_fanout_exchange交换机同时绑定了短信队列和邮件队列，因此下面这行代码会将消息通过
        *  message_fanout_exchange交换机同时传递给短息队列和邮件队列。
        */
        rabbitTemplate.convertAndSend(ExchangeEnum.FANOUT_MESSAGE.getName()
                , null, message);
    }

}
