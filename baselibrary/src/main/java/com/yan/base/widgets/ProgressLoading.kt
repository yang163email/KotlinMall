package com.yan.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import com.yan.base.R
import kotlinx.android.synthetic.main.progress_dialog.*

/**
 *  @author      : yan
 *  @date        : 2018/1/13 13:12
 *  @description : 自定义进度加载框
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animationDrawable: AnimationDrawable? = null

        fun create(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            return mDialog.apply {
                setContentView(R.layout.progress_dialog)    //自定义布局
                setCancelable(true) //可以取消
                setCanceledOnTouchOutside(false)    //外部点击不能取消
                window.attributes.gravity = Gravity.CENTER  //居中显示

                val lp = window.attributes
                lp.dimAmount = 0.2f
                window.attributes = lp

                animationDrawable = mIvLoading.background as AnimationDrawable  //获取ImageView中的drawable
            }
        }
    }

    /**
     * 显示dialog
     */
    fun showLoading() {
        super.show()
        animationDrawable?.start()
    }

    /**
     * 隐藏dialog
     */
    fun hideLoading() {
        super.dismiss()
        animationDrawable?.stop()
    }
}