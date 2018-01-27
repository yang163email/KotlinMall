package com.yan.order.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.order.presenter.view.OrderConfirmView
import com.yan.order.service.OrderService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 14:39
 *  @description : 确认订单页面P层
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.getOrderById(orderId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetOrderByIdResult(it) }
                }
    }

}