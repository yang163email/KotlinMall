package com.yan.order.service.impl

import com.yan.base.ext.convertBoolean
import com.yan.order.data.repository.ShipAddressRepository
import com.yan.order.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:50
 *  @description : 收货地址 业务层 实现类
 */
class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {

    @Inject
    lateinit var repository: ShipAddressRepository

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<Boolean> =
            repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
}