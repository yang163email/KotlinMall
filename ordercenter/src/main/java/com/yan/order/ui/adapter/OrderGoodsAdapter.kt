package com.yan.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.loadUrl
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.order.R
import com.yan.order.data.protocol.OrderGoods
import com.yan.order.ui.adapter.OrderGoodsAdapter.ViewHolder
import kotlinx.android.synthetic.main.layout_order_goods_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/27 16:01
 *  @description : 订单中商品列表
 */
class OrderGoodsAdapter(context: Context)
    : BaseRecyclerViewAdapter<OrderGoods, ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.apply {
            model.apply {
                mIvGoodsIcon.loadUrl(goodsIcon)
                mTvGoodsDesc.text = goodsDesc
                mTvGoodsSku.text = goodsSku
                mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(goodsPrice)
                mTvGoodsCount.text = "x$goodsCount"
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
