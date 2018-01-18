package com.yan.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.yan.base.R
import com.yan.base.common.AppManager
import org.jetbrains.anko.find

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:07
 *  @description : 无需P层的简单基类Activity
 */
open class BaseActivity : RxAppCompatActivity() {

    protected val TAG = javaClass.simpleName
    protected val contentView: View
        get() {
            val content = find<FrameLayout>(R.id.content)
            return content.getChildAt(0)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}