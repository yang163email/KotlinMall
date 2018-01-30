package com.yan.order.injection.module

import com.yan.order.service.ShipAddressService
import com.yan.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:56
 *  @description : 提供收货地址相关字段的module
 */
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressService(service: ShipAddressServiceImpl): ShipAddressService = service
}