package com.miracle.directproducer.service;

/**
 * @author : sungm
 * @date : 2021-03-30 12:01
 */
public interface OrderService {

    /**
     * 发送信息
     *
     * @param orderId       订单ID
     */
    void sendMessage(String orderId);

}
