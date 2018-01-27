package com.yan.order.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.order.data.protocol.Order

/**
 *  @author      : yan
 *  @date        : 2018/1/27 14:39
 *  @description : 确认订单页面回调层
 */
interface OrderConfirmView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}