package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.RegisterView
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 注册界面Presenter层
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        userService.register(mobile, pwd, verifyCode)
                .execute2(lifecycleProvider, mView) {
                    onNext { if (it) mView.onRegisterResult("注册成功") }
                }
    }
}