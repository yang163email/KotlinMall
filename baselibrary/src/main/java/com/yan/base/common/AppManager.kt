package com.yan.base.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 *  @author      : yan
 *  @date        : 2018/1/13 11:35
 *  @description : App管理器，管理所有Activity
 */
class AppManager private constructor() {

    companion object {
        val instance by lazy { AppManager() }
    }

    private val activityStack: Stack<Activity> = Stack()

    /**
     * 入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 栈顶Activity
     */
    fun currentActivity(): Activity = activityStack.lastElement()

    /**
     * 关闭所有Activity
     */
    fun finishAllActivity() {
        activityStack.forEach { it.finish() }
        activityStack.clear()
    }

    /**
     * 退出APP
     */
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}