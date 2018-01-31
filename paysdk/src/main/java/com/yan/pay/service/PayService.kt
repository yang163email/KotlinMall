package com.yan.pay.service

import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:15
 *  @description : 支付 业务层 接口
 */
interface PayService {

    /**
     * 获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>
}