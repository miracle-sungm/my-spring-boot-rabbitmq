package com.miracle.advancefeature.ttl.service.impl;

import com.miracle.advancefeature.ttl.service.TtlProducerService;
import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.RoutingKeyConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : sungm
 * @date : 2021-03-31 11:04
 */
@Service
public class TtlProducerServiceImpl implements TtlProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param message 消息
     */
    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(ExchangeEnum.TTL_DIRECT_MESSAGE.getName()
                , RoutingKeyConstant.DIRECT_TTL
                , message
                , m -> {
                    //设置内容编码格式
                    m.getMessageProperties().setContentEncoding("UTF-8");
                    //设置消息过期时间，单位毫秒
                    //由于队列中也设置了消息的过期时间，RabbitMQ会对比消息的过期时间，以时间较短的为准
                    m.getMessageProperties().setExpiration("6000");
                    return m;
                });
    }

}
