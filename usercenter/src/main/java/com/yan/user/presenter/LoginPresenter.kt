package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.LoginView
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 登录界面Presenter层
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        userService.login(mobile, pwd, pushId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onLoginResult(it) }
                }
    }
}