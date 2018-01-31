package com.yan.pay.injection.module

import com.yan.pay.service.PayService
import com.yan.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:22
 *  @description : 提供支付相关字段的module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService = payService
}