package com.yan.base.rx

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:26
 *  @description : 网络数据异常统一处理类
 */
class BaseException(val status: Int, val msg: String) : Throwable() {
}