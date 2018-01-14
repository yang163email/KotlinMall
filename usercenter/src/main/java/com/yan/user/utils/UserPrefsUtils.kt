package com.yan.user.utils

import com.yan.base.common.BaseConstant
import com.yan.base.utils.AppPrefsUtils
import com.yan.provider.common.ProviderConstant
import com.yan.user.data.protocol.UserInfo

/**
 *  @author      : yan
 *  @date        : 2018/1/14 11:35
 *  @description : 本地存储用户相关信息
 */
object UserPrefsUtils {

    /**
     * 退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.id ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NAME, userInfo?.userName ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
    }

    /**
     * 退出登录时，传入null,清空存储
     */
    fun getUserInfo(): UserInfo? {
        AppPrefsUtils.apply {
            val token= getString(BaseConstant.KEY_SP_TOKEN)
            val userIcon = getString(ProviderConstant.KEY_SP_USER_ICON)
            val userName = getString(ProviderConstant.KEY_SP_USER_NAME)
            val userMobile = getString(ProviderConstant.KEY_SP_USER_MOBILE)
            val userGender = getString(ProviderConstant.KEY_SP_USER_GENDER)
            val userSign = getString(ProviderConstant.KEY_SP_USER_SIGN)

            return UserInfo(token, userIcon, userName, userGender, userMobile, userSign)
        }
        return null
    }
}
