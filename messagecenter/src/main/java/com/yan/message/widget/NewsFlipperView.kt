package com.yan.message.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.yan.message.R
import kotlinx.android.synthetic.main.layout_news_flipper.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.px2sp

/**
 *  @author      : yan
 *  @date        : 2018/1/14 15:59
 *  @description : 公告组件封装
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_news_flipper, this)

        mFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out)
    }

    /**
     * 设置公告数据
     */
    fun setData(data: Array<String>) {
        data.forEach { mFlipperView.addView(buildNewsView(it)) }
        mFlipperView.startFlipping()
    }

    /**
     * 构建公告
     */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return textView
    }
}
