package com.miracle.advancefeature.dt.service;

/**
 * @author : sungm
 * @date : 2021-03-31 17:44
 */
public interface OrderService {

    /**
     * 生成订单
     *
     * @param orderContent  订单内容
     */
    void generateOrder(String orderContent);

}
