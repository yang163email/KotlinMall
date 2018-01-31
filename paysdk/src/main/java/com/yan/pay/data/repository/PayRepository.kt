package com.yan.pay.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.pay.data.api.PayApi
import com.yan.pay.data.protocol.GetPaySignReq
import com.yan.pay.data.protocol.PayOrderReq
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:14
 *  @description : 支付数据层
 */
class PayRepository @Inject constructor() {

    /**
     * 获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(PayApi::class.java)
                    .getPaySign(GetPaySignReq(orderId, totalPrice))

    /**
     * 刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))

}
