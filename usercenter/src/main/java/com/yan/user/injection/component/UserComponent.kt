package com.yan.user.injection.component

import com.yan.user.injection.module.UserModule
import com.yan.user.ui.activity.RegisterActivity
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/12 20:33
 *  @description : 用户模块Component，关联UserModule
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)
}