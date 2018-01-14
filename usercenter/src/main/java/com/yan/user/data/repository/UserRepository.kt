package com.yan.user.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.user.data.api.UserApi
import com.yan.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:17
 *  @description : 用户仓库类，Retrofit调用网络接口
 */
class UserRepository @Inject constructor() {

    /**
     * 注册
     */
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> =
        RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile, pwd, verifyCode))

    /**
     * 登录
     */
    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> =
            RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile, pwd, pushId))

    /**
     * 忘记密码
     */
    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(mobile, verifyCode))

    /**
     * 重置密码
     */
    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(mobile, pwd))

    /**
     * 修改用户资料
     */
    fun editUser(userIcon: String, userName: String,
                 userGender: String, userSign: String): Observable<BaseResp<UserInfo>> =
            RetrofitFactory.instance.create(UserApi::class.java)
                    .editUser(EditUserReq(userIcon, userName, userGender, userSign))

}