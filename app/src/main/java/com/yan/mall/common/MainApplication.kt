package com.yan.mall.common

import cn.jpush.android.api.JPushInterface
import com.yan.base.common.BaseApplication

/**
 *  @author      : yan
 *  @date        : 2018/2/1 10:20
 *  @description : 主工程Application
 */
class MainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}