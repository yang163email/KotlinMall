package com.yan.base.ui.activity

import android.os.Bundle
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.yan.base.common.AppManager

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:07
 *  @description : 无需P层的简单基类Activity
 */
open class BaseActivity : RxAppCompatActivity() {

    protected val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}