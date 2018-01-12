package com.yan.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:12
 *  @description : Application级别的module
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity = activity
}