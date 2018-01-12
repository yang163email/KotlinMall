package com.yan.user.presenter

import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.RegisterView

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 注册界面Presenter层
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String) {
        //业务逻辑
        mView.onRegisterResult(true)
    }
}