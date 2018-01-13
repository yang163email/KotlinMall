package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:14
 *  @description : 登录请求bean
 */
data class LoginReq(val mobile: String, val pwd: String, val pushId: String)