package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:14
 *  @description : 忘记密码请求bean
 */
data class ForgetPwdReq(val mobile: String, val verifyCode: String)