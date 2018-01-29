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
import com.yan.order.data.protocol.Order
import com.yan.order.ui.adapter.OrderAdapter.OrderVH
import kotlinx.android.synthetic.main.layout_order_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/29 21:42
 *  @description : 订单fragment的adapter
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderVH>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OrderVH {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item, parent, false)
        return OrderVH(view)
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        super.onBindViewHolder(holder, position)
        val order = dataList[position]
        if (order.orderGoodsList.size == 1) {
            val orderGoods = order.orderGoodsList[0]
            holder.itemView.apply {
                mIvGoodsIcon.loadUrl(orderGoods.goodsIcon)
                mTvGoodsDesc.text = orderGoods.goodsDesc
                mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)
                mTvGoodsCount.text = "x${orderGoods.goodsCount}"
                mTvOrderInfo.text = "合计${orderGoods.goodsCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(order.totalPrice)}"
            }
        }
    }

    class OrderVH(itemView: View?) : RecyclerView.ViewHolder(itemView)
}