package com.yan.user.presenter.view

import com.yan.base.presenter.view.BaseView

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:14
 *  @description : 重置密码界面View层，负责回调结果
 */
interface ResetPwdView : BaseView {

    fun onResetPwdResult(result: String)
}