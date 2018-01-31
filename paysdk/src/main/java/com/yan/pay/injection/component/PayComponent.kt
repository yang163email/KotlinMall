package com.yan.pay.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.pay.injection.module.PayModule
import com.yan.pay.ui.activity.CashRegisterActivity
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:23
 *  @description : 支付模块component，关联PayModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(PayModule::class))
interface PayComponent {

    fun inject(activity: CashRegisterActivity)
}