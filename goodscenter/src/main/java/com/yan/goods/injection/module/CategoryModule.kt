package com.yan.goods.injection.module

import com.yan.goods.service.CategoryService
import com.yan.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/15 11:50
 *  @description : 提供商品分类相关字段的module
 */
@Module
class CategoryModule {

    @Provides
    fun provideCategoryService(service: CategoryServiceImpl): CategoryService = service
}