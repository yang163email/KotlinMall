package com.yan.user.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.user.data.protocol.LoginReq
import com.yan.user.data.protocol.RegisterReq
import com.yan.user.data.protocol.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:12
 *  @description : 用户相关的api请求接口
 */
interface UserApi {

    /**
     * 注册
     */
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    /**
     * 登录
     */
    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>
}