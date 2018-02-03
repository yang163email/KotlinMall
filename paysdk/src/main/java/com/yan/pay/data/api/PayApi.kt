package com.yan.pay.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.pay.data.protocol.GetPaySignReq
import com.yan.pay.data.protocol.PayOrderReq
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:13
 *  @description : 支付 接口
 */
interface PayApi {

    /**
     * 获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /**
     * 刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>

}
