package com.yan.pay.service.impl

import com.yan.base.ext.convert
import com.yan.base.ext.convertBoolean
import com.yan.pay.data.repository.PayRepository
import com.yan.pay.service.PayService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:16
 *  @description : 支付业务实现层
 */
class PayServiceImpl @Inject constructor() : PayService {

    @Inject
    lateinit var repository: PayRepository

    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> =
            repository.getPaySign(orderId, totalPrice).convert()

    override fun payOrder(orderId: Int): Observable<Boolean> =
            repository.payOrder(orderId).convertBoolean()
}