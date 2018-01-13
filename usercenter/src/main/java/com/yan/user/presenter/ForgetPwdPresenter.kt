package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.ForgetPwdView
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 忘记密码界面Presenter层
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        userService.forgetPwd(mobile, verifyCode)
                .execute2(lifecycleProvider, mView) {
                    onNext { if (it) mView.onForgetPwdResult("认证成功") }
                }
    }
}