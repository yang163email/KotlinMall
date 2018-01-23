package com.yan.base.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.yan.base.R
import com.yan.base.ext.onClick
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/13 12:12
 *  @description : 自定义头部bar
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isShowBack = true
    private var titleText: String? = null
    private var rightText: String? = null
    private val mContext: Context

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_header_bar, this)
        mContext = context
        val ta = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = ta.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = ta.getString(R.styleable.HeaderBar_titleText)
        rightText = ta.getString(R.styleable.HeaderBar_rightText)

        initView()
        ta.recycle()
    }

    private fun initView() {
        mIvLeft.visibility = if (isShowBack) View.VISIBLE else View.GONE
        titleText?.let { mTvTitle.text = it }
        rightText?.let {
            mTvRight.text = it
            mTvRight.visibility = View.VISIBLE
        }

        mIvLeft.onClick {
            if (mContext is Activity) { mContext.finish() }
        }
    }

    fun getRightView(): TextView = mTvRight

    fun getRightText(): String = mTvRight.text.toString()
}