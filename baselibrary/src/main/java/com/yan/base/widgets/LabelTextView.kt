package com.yan.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.yan.base.R
import kotlinx.android.synthetic.main.layout_label_textview.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/30 20:19
 *  @description : LabelTextView封装
 */
class LabelTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mLabelText: CharSequence? = null
    private var mContentText: CharSequence? = null

    /**
     * 初始化属性
     */
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelText)
        this.mLabelText = typedArray.getText(R.styleable.LabelText_labelText)
        this.mContentText = typedArray.getText(R.styleable.LabelText_contentText)
        initView()
        typedArray.recycle()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        View.inflate(context, R.layout.layout_label_textview, this)

        mLabelText?.let { mTvLabel.text = it }

        mContentText?.let { mTvContent.text = it }

    }

    /**
     * 设置内容文本
     */
    fun setContentText(text:String){
        mTvContent.text = text
    }
}
