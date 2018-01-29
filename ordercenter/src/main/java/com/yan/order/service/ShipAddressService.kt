package com.yan.order.service

import com.yan.order.data.protocol.ShipAddress
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

    /**
     * 获取收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    /**
     * 修改收货地址
     */
    fun editShipAddress(address: ShipAddress): Observable<Boolean>

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<Boolean>
}