package com.yan.goods.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.goods.injection.module.CategoryModule
import com.yan.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/15 11:51
 *  @description : 商品分类模块Component，关联CategoryModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CategoryModule::class))
interface CategoryComponent {

    fun inject(fragment: CategoryFragment)
}