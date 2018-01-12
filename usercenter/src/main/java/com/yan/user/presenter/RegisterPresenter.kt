package com.yan.user.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.user.presenter.view.RegisterView
import com.yan.user.service.UserService
import javax.inject.Inject
import javax.inject.Named

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 注册界面Presenter层
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    @field:Named("service")
    lateinit var userService: UserService

    @Inject
    @field:Named("service2")
    lateinit var userService2: UserService

    fun register(mobile: String, verifyCode: String, pwd: String) {
        //业务逻辑
//        val userService = UserServiceImpl()
        //第一种
//        userService.register(mobile, verifyCode, pwd)
//                .execute(object : BaseSubscriber<Boolean>() {
//                    override fun onNext(t: Boolean) {
//                        mView.onRegisterResult(t)
//                    }
//                })
        //第二种
//        userService.register(mobile, verifyCode, pwd)
//                .execute1(
//                        onNext = {},
//                        onError = {},
//                        onComplete = {}
//                )
        //第三种
        userService.register(mobile, verifyCode, pwd)
                .execute2 {
                    onNext { mView.onRegisterResult(it) }
                    onError {  }
                    onComplete {  }
                }
    }

    fun register2(mobile: String, verifyCode: String, pwd: String) {
        //业务逻辑
        userService2.register(mobile, verifyCode, pwd)
                .execute2 {
                    onNext { mView.onRegisterResult(it) }
                    onError {  }
                    onComplete {  }
                }
    }
}