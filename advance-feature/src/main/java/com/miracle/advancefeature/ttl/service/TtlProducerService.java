package com.miracle.advancefeature.ttl.service;

/**
 * @author : sungm
 * @date : 2021-03-31 11:03
 */
public interface TtlProducerService {

    /**
     * 发送消息
     *
     * @param message 消息
     */
    void sendMessage(String message);

}
