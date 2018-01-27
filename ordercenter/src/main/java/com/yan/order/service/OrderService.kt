package com.yan.order.service

import com.yan.order.data.protocol.Order
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/27 14:35
 *  @description : 订单 业务层 接口
 */
interface OrderService {

    /**
     * 提交订单
     */
    fun getOrderById(orderId: Int): Observable<Order>
}