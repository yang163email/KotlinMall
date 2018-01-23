package com.yan.goods.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.goods.injection.module.CartModule
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:45
 *  @description : 购物车模块Component，关联CartModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CartModule::class))
interface CartComponent {

}