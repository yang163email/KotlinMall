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
     * 根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<Order>

    /**
     * 提交订单
     */
    fun submitOrder(order: Order): Observable<Boolean>

    /**
     * 根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

    /**
     * 确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>

    /**
     * 取消订单
     */
    fun cancelOrder(orderId: Int): Observable<Boolean>
}