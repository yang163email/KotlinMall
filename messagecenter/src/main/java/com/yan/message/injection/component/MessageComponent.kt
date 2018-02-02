package com.yan.message.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.message.injection.module.MessageModule
import com.yan.message.ui.fragment.MessageFragment
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:10
 *  @description : 消息模块Component，关联MessageModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(MessageModule::class))
interface MessageComponent {

    fun inject(fragment: MessageFragment)
}