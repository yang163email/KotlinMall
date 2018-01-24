package com.yan.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.yan.base.alias.T0_Unit
import com.yan.base.common.BaseConstant
import com.yan.base.utils.AppPrefsUtils
import com.yan.provider.router.RouterPath

/**
 *  @author      : yan
 *  @date        : 2018/1/14 21:48
 *  @description : 常用业务逻辑文件
 */

/** 是否登录 */
fun isLogin() = AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()

/**
 * 登录之后的操作，已经做了未登录的处理
 */
fun afterLogin(block: T0_Unit) {
    if (isLogin()) block()
    else ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
}