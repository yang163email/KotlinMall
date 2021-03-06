package com.yan.order.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.order.data.protocol.ShipAddress
import com.yan.order.presenter.view.ShipAddressView
import com.yan.order.service.ShipAddressService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/29 10:45
 *  @description : 收货地址列表页面P层
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var orderService: ShipAddressService

    fun getShipAddressList() {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.getShipAddressList()
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetShipAddressListResult(it) }
                }
    }

    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.editShipAddress(address)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onEditShipAddressResult(it) }
                }
    }

    fun deleteShipAddress(id: Int) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.deleteShipAddress(id)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onDeleteShipAddressResult(it) }
                }
    }
}