package com.yan.user.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/13 17:15
 *  @description : 用户信息实体类
 */
data class UserInfo(
        val id: Long,
        val userName: String,
        val userMobile: String,
        val pushId: String
)