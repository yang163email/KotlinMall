package com.yan.order.presenter.view

import com.yan.base.presenter.view.BaseView

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:54
 *  @description : 编辑收货地址页面回调层
 */
interface ShipAddressEditView : BaseView {

    fun onAddShipAddressResult(result: Boolean)
    fun onEditShipAddressResult(result: Boolean)
}