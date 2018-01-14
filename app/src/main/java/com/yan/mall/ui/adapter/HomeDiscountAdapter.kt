package com.yan.mall.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.base.utils.GlideUtils
import com.yan.mall.R
import com.yan.mall.ui.adapter.HomeDiscountAdapter.HomeDiscountVH
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 16:36
 *  @description : 首页折扣RecyclerView的adapter
 */
class HomeDiscountAdapter(context: Context)
    : BaseRecyclerViewAdapter<String, HomeDiscountVH>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeDiscountVH {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item, parent, false)
        return HomeDiscountVH(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeDiscountVH, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.apply {
            //加载图片
            GlideUtils.loadUrlImage(mContext, dataList[position], mIvGoodsIcon)
            //静态假数据
            mTvDiscountAfter.text = "￥123.00"
            mTvDiscountBefore.text = "$1000.00"
        }
    }

    class HomeDiscountVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            //设置TextView样式
            itemView.mTvDiscountBefore.paint.apply {
                flags = Paint.STRIKE_THRU_TEXT_FLAG
                isAntiAlias = true
            }
        }
    }
}