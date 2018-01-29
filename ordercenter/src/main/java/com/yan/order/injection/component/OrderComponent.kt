package com.yan.order.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.ui.activity.OrderConfirmActivity
import com.yan.order.ui.fragment.OrderFragment
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/27 14:50
 *  @description : 订单模块Component，关联OrderModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(OrderModule::class))
interface OrderComponent {

    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
}