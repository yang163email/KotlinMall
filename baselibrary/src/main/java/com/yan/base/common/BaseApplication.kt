package com.yan.base.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.yan.base.injection.component.AppComponent
import com.yan.base.injection.component.DaggerAppComponent
import com.yan.base.injection.module.AppModule

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:11
 *  @description : application的基类
 */
class BaseApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()
        context = this

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}