package com.yan.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.alias.T2_Unit
import com.yan.base.ext.loadUrl
import com.yan.base.ext.onClick
import com.yan.base.ext.setVisible
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.common.OrderStatus
import com.yan.order.data.protocol.Order
import com.yan.order.ui.adapter.OrderAdapter.OrderVH
import kotlinx.android.synthetic.main.layout_order_item.view.*
import org.jetbrains.anko.dip

/**
 *  @author      : yan
 *  @date        : 2018/1/29 21:42
 *  @description : 订单fragment的adapter
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderVH>(context) {

    var onOptClickListener: T2_Unit<Int, Order>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OrderVH {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_item, parent, false)
        return OrderVH(view)
    }

    override fun onBindViewHolder(holder: OrderVH, position: Int) {
        super.onBindViewHolder(holder, position)
        val order = dataList[position]

        var totalCount = 0
        if (order.orderGoodsList.size == 1) {
            val orderGoods = order.orderGoodsList[0]
            holder.itemView.apply {
                mMultiGoodsView.setVisible(false)
                mSingleGoodsView.setVisible(true)

                mIvGoodsIcon.loadUrl(orderGoods.goodsIcon)
                mTvGoodsDesc.text = orderGoods.goodsDesc
                mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)
                totalCount = orderGoods.goodsCount
                mTvGoodsCount.text = "x$totalCount"
            }
        } else {
            holder.itemView.apply {
                mMultiGoodsView.setVisible(true)
                mSingleGoodsView.setVisible(false)
                mMultiGoodsView.removeAllViews()

                order.orderGoodsList.forEach {
                    val imageView = ImageView(mContext)
                    val lp = ViewGroup.MarginLayoutParams(mContext.dip(60), mContext.dip(60))
                    lp.rightMargin = mContext.dip(15)
                    imageView.layoutParams = lp
                    imageView.loadUrl(it.goodsIcon)

                    mMultiGoodsView.addView(imageView)
                    totalCount += it.goodsCount
                }
            }
        }
        holder.itemView.mTvOrderInfo.text = "合计${totalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(order.totalPrice)}"

        //根据状态显示不同按钮
        when (order.orderStatus) {
            OrderStatus.ORDER_WAIT_PAY -> {
                holder.itemView.mTvOrderStatusName.text = "待付款"
                holder.itemView.mTvOrderStatusName.setTextColor(mContext.resources.getColor(R.color.common_red))
                setOptVisible(false, true, true, holder)
            }
            OrderStatus.ORDER_WAIT_CONFIRM -> {
                holder.itemView.mTvOrderStatusName.text = "待收货"
                holder.itemView.mTvOrderStatusName.setTextColor(mContext.resources.getColor(R.color.common_blue))
                setOptVisible(true, false, true, holder)
            }
            OrderStatus.ORDER_COMPLETED -> {
                holder.itemView.mTvOrderStatusName.text = "已完成"
                holder.itemView.mTvOrderStatusName.setTextColor(mContext.resources.getColor(R.color.common_yellow))
                setOptVisible(false, false, false, holder)
            }
            OrderStatus.ORDER_CANCELED -> {
                holder.itemView.mTvOrderStatusName.text = "已取消"
                holder.itemView.mTvOrderStatusName.setTextColor(mContext.resources.getColor(R.color.common_gray))
                setOptVisible(false, false, false, holder)
            }
        }
        holder.itemView.apply {
            mBtnConfirm.onClick { onOptClickListener?.invoke(OrderConstant.OPT_ORDER_CONFIRM, order) }
            mBtnPay.onClick { onOptClickListener?.invoke(OrderConstant.OPT_ORDER_PAY, order) }
            mBtnCancel.onClick { onOptClickListener?.invoke(OrderConstant.OPT_ORDER_CANCEL, order) }
        }
    }

    private fun setOptVisible(confirmVisible: Boolean, waitPayVisible: Boolean, cancelVisible: Boolean, holder: OrderVH) {
        holder.itemView.apply {
            mBtnConfirm.setVisible(confirmVisible)
            mBtnPay.setVisible(waitPayVisible)
            mBtnCancel.setVisible(cancelVisible)

            mBottomView.setVisible(confirmVisible || waitPayVisible || cancelVisible)
        }
    }

    class OrderVH(itemView: View?) : RecyclerView.ViewHolder(itemView)

}