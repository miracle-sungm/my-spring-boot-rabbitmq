package com.miracle.advancefeature.dt.repository;

import com.miracle.advancefeature.dt.entity.Order;
import org.apache.ibatis.annotations.*;

/**
 * @author : sungm
 * @date : 2021-03-31 17:06
 */
@Mapper
public interface OrderMapper {

    /**
     * save
     *
     * @param order o
     */
    @Options(keyProperty = "orderId", useGeneratedKeys = true)
    @Insert("insert into rabbit_order(order_content, created_time, status) " +
            "values (#{order.orderContent}, #{order.createdTime}, #{order.status})")
    void save(@Param(value = "order") Order order);

}
