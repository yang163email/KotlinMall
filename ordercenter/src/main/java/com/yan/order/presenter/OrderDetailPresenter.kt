package com.yan.order.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.order.presenter.view.OrderDetailView
import com.yan.order.service.OrderService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/30 20:23
 *  @description : 订单详情页面P层
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

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