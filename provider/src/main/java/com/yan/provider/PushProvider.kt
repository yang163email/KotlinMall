package com.yan.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 *  @author      : yan
 *  @date        : 2018/2/1 17:42
 *  @description : 跨模块接口调用 接口定义
 */
interface PushProvider : IProvider {

    fun getPushId(): String
}