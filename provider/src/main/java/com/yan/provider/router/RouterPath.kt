package com.yan.provider.router

/**
 *  @author      : yan
 *  @date        : 2018/1/20 14:02
 *  @description : 模块路由，路径定义
 */
object RouterPath {
    //用户模块
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }
    //订单模块
    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }
    //支付模块
    class PaySDK {
        companion object {
            const val PATH_PAY = "/paySdk/pay"
        }
    }
}