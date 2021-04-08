package com.miracle.advancefeature.dt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.miracle.advancefeature.dt.constant.OrderStatusEnum;
import com.miracle.advancefeature.dt.constant.QueueStatusEnum;
import com.miracle.advancefeature.dt.entity.Order;
import com.miracle.advancefeature.dt.entity.OrderQueue;
import com.miracle.advancefeature.dt.repository.OrderMapper;
import com.miracle.advancefeature.dt.repository.OrderQueueMapper;
import com.miracle.advancefeature.dt.service.OrderService;
import com.miracle.common.api.constant.ExchangeEnum;
import com.miracle.common.api.constant.RoutingKeyConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author : sungm
 * @date : 2021-03-31 17:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderQueueMapper orderQueueMapper;

    /**
     * 生成订单
     *
     * @param orderContent 订单内容
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void generateOrder(String orderContent) {
        Order order = new Order(null, orderContent, LocalDateTime.now()
                , OrderStatusEnum.就绪.getValue());
        orderMapper.save(order);

        notifyDelivery(order);
    }

    /**
     * 通知发货
     *
     * @param order     order
     */
    private void notifyDelivery(Order order) {
        rabbitTemplate.convertAndSend(ExchangeEnum.DT_DIRECT_MESSAGE.getName(), RoutingKeyConstant.DIRECT_DT
                , JSONObject.toJSONString(order));
        OrderQueue orderQueue = new OrderQueue();
        orderQueue.setOrderId(order.getOrderId());
        orderQueue.setOrderContent(order.getOrderContent());
        orderQueue.setStatus(order.getStatus());
        orderQueue.setCreatedTime(order.getCreatedTime());
        orderQueue.setQueueStatus(QueueStatusEnum.已发送.getValue());
        orderQueueMapper.save(orderQueue);
    }

}
