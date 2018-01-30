package com.yan.order.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.order.data.protocol.Order

/**
 *  @author      : yan
 *  @date        : 2018/1/29 21:47
 *  @description : 订单列表页面回调层
 */
interface OrderListView : BaseView {

    fun onGetOrderListResult(result: MutableList<Order>?)
    fun onConfirmOrderResult(result: Boolean)
    fun onCancelOrderResult(result: Boolean)
}