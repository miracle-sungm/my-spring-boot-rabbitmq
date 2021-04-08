package com.miracle.advancefeature.dt.repository;

import com.miracle.advancefeature.dt.entity.OrderQueue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : sungm
 * @date : 2021-04-01 11:53
 */
@Mapper
public interface OrderQueueMapper {

    /**
     * save
     *
     * @param orderQueue o
     */
    @Insert("insert into rabbit_order_queue(order_id, order_content, created_time, status, queue_status) " +
            "values (#{orderQueue.orderId}, #{orderQueue.orderContent}, #{orderQueue.createdTime}" +
            ", #{orderQueue.status}, #{orderQueue.queueStatus})")
    void save(@Param(value = "orderQueue") OrderQueue orderQueue);

}
