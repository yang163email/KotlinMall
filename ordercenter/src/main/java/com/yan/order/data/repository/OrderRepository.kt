package com.yan.order.data.repository


import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.order.data.api.OrderApi
import com.yan.order.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:33
 *  @description : 订单数据层
 */
class OrderRepository @Inject constructor() {

    /**
     * 取消订单
     */
    fun cancelOrder(orderId: Int): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(OrderApi::class.java).cancelOrder(CancelOrderReq(orderId))

    /**
     * 确认订单
     */
    fun confirmOrder(orderId: Int): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(OrderApi::class.java).confirmOrder(ConfirmOrderReq(orderId))

    /**
     * 根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<BaseResp<Order>> =
            RetrofitFactory.instance.create(OrderApi::class.java).getOrderById(GetOrderByIdReq(orderId))

    /**
     * 根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<BaseResp<MutableList<Order>?>> =
            RetrofitFactory.instance.create(OrderApi::class.java).getOrderList(GetOrderListReq(orderStatus))

    /**
     * 提交订单
     */
    fun submitOrder(order: Order): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(OrderApi::class.java).submitOrder(SubmitOrderReq(order))

}
