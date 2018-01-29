package com.yan.order.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.order.data.protocol.ShipAddress

/**
 *  @author      : yan
 *  @date        : 2018/1/29 10:47
 *  @description : 收货地址列表页面回调层
 */
interface ShipAddressView : BaseView {

    fun onGetShipAddressListResult(result: MutableList<ShipAddress>?)
}