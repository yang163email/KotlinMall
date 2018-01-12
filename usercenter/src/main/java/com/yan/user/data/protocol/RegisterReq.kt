package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:14
 *  @description : 注册请求bean
 */
data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String)