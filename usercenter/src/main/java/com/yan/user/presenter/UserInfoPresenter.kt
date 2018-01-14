package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.UserInfoView
import com.yan.user.service.UploadService
import com.yan.user.service.UserService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 用户信息界面Presenter层
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadService

    @Inject
    lateinit var userService: UserService

    fun getUploadToken() {
        if (!checkNetWork())
            return

        mView.showLoading()
        uploadService.getUploadToken().execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetUploadTokenResult(it) }
                }
    }

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork())
            return

        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onEditUserResult(it) }
                }
    }

}