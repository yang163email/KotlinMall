package com.yan.base.widgets

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.Button
import com.yan.base.R
import com.yan.base.alias.T0_Unit

/**
 *  @author      : yan
 *  @date        : 2018/1/13 14:32
 *  @description : 获取验证码按钮,带倒计时
 */
class VerifyButton(mContext: Context, attrs: AttributeSet) : Button(mContext, attrs) {

    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: T0_Unit? = null

    init {
        this.text = "获取验证码"
        mHandler = Handler()
    }

    /**
     * 倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
        mOnVerifyBtnClick?.invoke()
    }

    /**
     * 倒计时
     */
    private val countDown = object : Runnable {
        override fun run() {
            this@VerifyButton.text = "${mCount.toString()}s "
            this@VerifyButton.setBackgroundColor(resources.getColor(R.color.common_disable))
            this@VerifyButton.setTextColor(resources.getColor(R.color.common_white))
            this@VerifyButton.isEnabled = false

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunnable() {
        mHandler.removeCallbacks(countDown)
    }

    /**
     * 恢复到初始状态
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(resources.getColor(R.color.transparent))
        this.setTextColor(resources.getColor(R.color.common_blue))
        mCount = 60
    }

    /**
     * 点击事件
     */
    fun setOnVerifyBtnClick(onVerifyBtnClick: T0_Unit) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}
