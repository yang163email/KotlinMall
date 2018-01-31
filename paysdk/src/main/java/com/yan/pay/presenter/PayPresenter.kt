package com.yan.pay.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.pay.presenter.view.PayView
import com.yan.pay.service.PayService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:18
 *  @description : 支付P层
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var service: PayService

    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) return
        mView.showLoading()

        service.getPaySign(orderId, totalPrice).execute2(lifecycleProvider, mView) {
            onNext { mView.onGetSignResult(it) }
        }
    }
}