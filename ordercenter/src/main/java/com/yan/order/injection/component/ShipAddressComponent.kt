package com.yan.order.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.order.injection.module.ShipAddressModule
import com.yan.order.ui.activity.ShipAddressActivity
import com.yan.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:57
 *  @description : 收货地址模块Component，关联ShipAddressModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {

    fun inject(activity: ShipAddressEditActivity)
    fun inject(activity: ShipAddressActivity)
}