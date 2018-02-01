package com.yan.message.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface


/**
 *  @author      : yan
 *  @date        : 2018/2/1 14:12
 *  @description : 推送消息接收广播
 */
class MessageReceiver : BroadcastReceiver() {
    val TAG = javaClass.simpleName

    override fun onReceive(context: Context?, intent: Intent) {

        val bundle = intent.extras
        Log.d(TAG, "onReceive - " + intent.action + ", extras: " + bundle)

        if (JPushInterface.ACTION_REGISTRATION_ID == intent.action) {
            Log.d(TAG, "JPush用户注册成功")

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action) {
            Log.d(TAG, "接受到推送下来的自定义消息")
            Log.d(TAG, "onReceive: ${bundle.getString(JPushInterface.EXTRA_MESSAGE)}")
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action) {
            Log.d(TAG, "接受到推送下来的通知")

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action) {
            Log.d(TAG, "用户点击打开了通知")

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.action)
        }
    }
}