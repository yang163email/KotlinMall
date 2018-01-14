package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/14 11:27
 *  @description : 修改用户资料请求体
 */
data class EditUserReq(
        val userIcon: String,
        val userName: String,
        val gender: String,
        val sign: String
)
