package com.yan.user.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.user.data.protocol.UserInfo

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:14
 *  @description : 登录界面View层，负责回调结果
 */
interface LoginView : BaseView {

    fun onLoginResult(result: UserInfo)
}