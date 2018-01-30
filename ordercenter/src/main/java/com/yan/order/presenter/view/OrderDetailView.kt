package com.yan.order.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.order.data.protocol.Order

/**
 *  @author      : yan
 *  @date        : 2018/1/30 20:24
 *  @description : 订单详情页面回调层
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}