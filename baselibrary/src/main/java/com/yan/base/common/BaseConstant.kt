package com.yan.base.common

/**
 *  @author      : yan
 *  @date        : 2018/1/12 17:26
 *  @description : baseLibrary中的常量类
 */
class BaseConstant {

    companion object {
        /** 服务器地址 */
        const val SERVER_ADDRESS = "http://120.79.59.193:8080/Kotlin_Server/"

        //七牛服务地址
        const val IMAGE_SERVER_ADDRESS = "http://osea2fxp7.bkt.clouddn.com/"

        const val CONNECT_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L

        const val TABLE_PREFS = "KotlinMall"

        const val KEY_SP_TOKEN = "token"
    }

}