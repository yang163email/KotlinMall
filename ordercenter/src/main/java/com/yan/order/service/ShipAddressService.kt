package com.yan.order.service

import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:49
 *  @description : 收货地址 业务层 接口
 */
interface ShipAddressService {

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<Boolean>
}