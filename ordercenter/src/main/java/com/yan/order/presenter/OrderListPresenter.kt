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

    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.confirmOrder(orderId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onConfirmOrderResult(it) }
                }
    }

    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.cancelOrder(orderId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onCancelOrderResult(it) }
                }
    }

}