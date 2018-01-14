package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:14
 *  @description : 重置密码请求bean
 */
data class ResetPwdReq(val mobile: String, val pwd: String)