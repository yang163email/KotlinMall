package com.yan.order.data.api

import com.yan.order.data.protocol.AddShipAddressReq
import com.yan.order.data.protocol.DeleteShipAddressReq
import com.yan.order.data.protocol.EditShipAddressReq
import com.yan.order.data.protocol.ShipAddress
import com.yan.base.data.protocol.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:26
 *  @description : 地址管理 接口
 */
interface ShipAddressApi {

    /**
     * 添加收货地址
     */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResp<String>>

    /**
     * 删除收货地址
     */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResp<String>>

    /**
     * 修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressReq): Observable<BaseResp<String>>

    /**
     * 查询收货地址列表
     */
    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>>

}
