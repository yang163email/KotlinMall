package com.yan.order.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.order.presenter.view.ShipAddressEditView
import com.yan.order.service.ShipAddressService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:52
 *  @description : 编辑收货地址页面P层
 */
class ShipAddressEditPresenter @Inject constructor() : BasePresenter<ShipAddressEditView>() {

    @Inject
    lateinit var orderService: ShipAddressService

    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) return
        mView.showLoading()

        orderService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onAddShipAddressResult(it) }
                }
    }

}