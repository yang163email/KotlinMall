package com.yan.mall.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.loadUrl
import com.yan.mall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 17:39
 *  @description : 首页话题数据ViewPager适配器
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {

    override fun destroyItem(parent: ViewGroup, paramInt: Int, paramObject: Any) {
        parent.removeView(paramObject as View)
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, parent, false)
        rooView.mIvTopic.loadUrl(list[position])
        parent.addView(rooView)
        return rooView
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView === paramObject
    }
}
