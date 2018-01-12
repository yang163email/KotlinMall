package com.yan.base.injection.component

import android.content.Context
import com.yan.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:15
 *  @description : Application级别的component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context
}