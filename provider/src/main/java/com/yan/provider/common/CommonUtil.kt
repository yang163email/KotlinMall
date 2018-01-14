package com.yan.provider.common

import com.yan.base.common.BaseConstant
import com.yan.base.utils.AppPrefsUtils

/**
 *  @author      : yan
 *  @date        : 2018/1/14 21:48
 *  @description : 常用业务逻辑文件
 */

/** 是否登录 */
fun isLogin() = AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()