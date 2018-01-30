package com.yan.order.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.order.data.api.ShipAddressApi
import com.yan.order.data.protocol.AddShipAddressReq
import com.yan.order.data.protocol.DeleteShipAddressReq
import com.yan.order.data.protocol.EditShipAddressReq
import com.yan.order.data.protocol.ShipAddress
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:35
 *  @description : 收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /**
     * 添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(ShipAddressApi::class.java)
                    .addShipAddress(AddShipAddressReq(shipUserName, shipUserMobile, shipAddress))

    /**
     * 删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(ShipAddressApi::class.java)
                    .deleteShipAddress(DeleteShipAddressReq(id))

    /**
     * 修改收货地址
     */
    fun editShipAddress(address: ShipAddress): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(ShipAddressApi::class.java)
                    .editShipAddress(EditShipAddressReq(address.id, address.shipUserName,
                            address.shipUserMobile, address.shipAddress, address.shipIsDefault))

    /**
     * 获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>> =
            RetrofitFactory.instance.create(ShipAddressApi::class.java).getShipAddressList()

}
