package com.yan.goods.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.goods.injection.module.GoodsModule
import com.yan.goods.ui.activity.GoodsActivity
import com.yan.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:08
 *  @description : 商品模块Component，关联GoodsModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(GoodsModule::class))
interface GoodsComponent {

    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}