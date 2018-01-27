package com.yan.order.injection.module

import com.yan.order.service.OrderService
import com.yan.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:08
 *  @description : 提供商品相关字段的module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderService(service: OrderServiceImpl): OrderService = service
}