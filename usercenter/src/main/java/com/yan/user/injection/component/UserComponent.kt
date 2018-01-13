package com.yan.user.injection.component

import com.yan.base.injection.PerComponentScope
import com.yan.base.injection.component.ActivityComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.ui.activity.*
import dagger.Component

/**
 *  @author      : yan
 *  @date        : 2018/1/12 20:33
 *  @description : 用户模块Component，关联UserModule
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}