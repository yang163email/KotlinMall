package com.yan.pay.presenter.view

import com.yan.base.presenter.view.BaseView

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:18
 *  @description : 支付v层接口
 */
interface PayView : BaseView {

    fun onGetSignResult(result: String)
    fun onPayOrderResult(result: Boolean)
}