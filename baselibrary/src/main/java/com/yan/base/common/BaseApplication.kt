package com.yan.base.common

import android.app.Application
import com.yan.base.injection.component.AppComponent
import com.yan.base.injection.component.DaggerAppComponent
import com.yan.base.injection.module.AppModule

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:11
 *  @description : application的基类
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}