package com.yan.user.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.user.data.api.UserApi
import com.yan.user.data.protocol.RegisterReq
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

}