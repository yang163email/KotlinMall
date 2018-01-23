package com.yan.goods.injection.module

import com.yan.goods.service.CartService
import com.yan.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:43
 *  @description : 提供购物车相关字段的module
 */
@Module
class CartModule {

    @Provides
    fun provideCartService(service: CartServiceImpl): CartService = service
}