package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.ResetPwdView
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 重置密码界面Presenter层
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        userService.resetPwd(mobile, pwd)
                .execute2(lifecycleProvider, mView) {
                    onNext { if (it) mView.onResetPwdResult("重置密码成功") }
                }
    }
}