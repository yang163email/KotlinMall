package com.yan.order.service.impl

import com.yan.base.ext.convert
import com.yan.base.ext.convertBoolean
import com.yan.order.data.protocol.Order
import com.yan.order.data.repository.OrderRepository
import com.yan.order.service.OrderService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 14:37
 *  @description : 订单 业务层 实现类
 */
class OrderServiceImpl @Inject constructor() : OrderService {

    @Inject
    lateinit var repository: OrderRepository

    override fun getOrderById(orderId: Int): Observable<Order> =
            repository.getOrderById(orderId).convert()

    override fun submitOrder(order: Order): Observable<Boolean> =
            repository.submitOrder(order).convertBoolean()

}