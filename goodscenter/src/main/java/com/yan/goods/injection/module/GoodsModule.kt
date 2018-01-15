package com.yan.goods.injection.module

import com.yan.goods.service.GoodsService
import com.yan.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:08
 *  @description : 提供商品相关字段的module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodsService(service: GoodsServiceImpl): GoodsService = service
}