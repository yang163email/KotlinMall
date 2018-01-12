package com.yan.base.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:09
 *  @description : 所有网络响应数据的基类
 */
class BaseResp<out T>(val status: Int, val message: String, val data: T)