package com.yan.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.yan.base.R
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

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_header_bar, this)

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
    }
}