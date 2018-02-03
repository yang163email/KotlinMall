package com.yan.user.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

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

    /**
     * 忘记密码
     */
    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq): Observable<BaseResp<String>>

    /**
     * 重置密码
     */
    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq): Observable<BaseResp<String>>

    /**
     * 修改用户资料
     */
    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq): Observable<BaseResp<UserInfo>>
}