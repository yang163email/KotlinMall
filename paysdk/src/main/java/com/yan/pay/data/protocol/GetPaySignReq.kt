package com.yan.pay.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:13
 *  @description : 获取支付宝 支付签名
 */
data class GetPaySignReq(val orderId: Int, val totalPrice: Long)
