package com.yan.user.presenter

import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.UserInfoView
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 用户信息界面Presenter层
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

}