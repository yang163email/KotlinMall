package com.yan.base.injection.component

import android.app.Activity
import com.yan.base.injection.ActivityScope
import com.yan.base.injection.module.ActivityModule
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:15
 *  @description : Activity级别的component
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun activity(): Activity
}