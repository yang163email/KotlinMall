package com.yan.order.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.order.presenter.view.OrderListView
import com.yan.order.service.OrderService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/29 21:45
 *  @description : 订单列表页面P层
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderStatus: Int) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.getOrderList(orderStatus)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetOrderListResult(it) }
                }
    }

}