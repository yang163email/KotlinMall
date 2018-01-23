package com.yan.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.loadUrl
import com.yan.base.ext.onClick2
import com.yan.base.ext.onTextChangedListener
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.goods.R
import com.yan.goods.data.protocol.CartGoods
import com.yan.goods.event.CartAllCheckedEvent
import com.yan.goods.ext.getEditText
import com.yan.goods.ui.adapter.CartGoodsAdapter.ViewHolder
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/23 11:36
 *  @description : 购物车数据适配器
 */
class CartGoodsAdapter(context: Context)
    : BaseRecyclerViewAdapter<CartGoods, ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_cart_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.apply {
            model.apply {
                //是否选中
                mCbChecked.isChecked = isSelected
                //加载商品图片
                mIvGoodsIcon.loadUrl(goodsIcon)
                //商品描述
                mTvGoodsDesc.text = goodsDesc
                //商品SKU
                mTvGoodsSku.text = goodsSku
                //商品价格
                mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(goodsPrice)
                //商品数量
                mBtnGoodsCount.setCurrentNumber(goodsCount)
            }

            //选中按钮事件
            mCbChecked.onClick2 {
                model.isSelected = it.isChecked
                val isAllSelected = dataList.all { it.isSelected }
                Bus.send(CartAllCheckedEvent(isAllSelected))
            }
            //商品数量变化监听
            mBtnGoodsCount.getEditText().onTextChangedListener {
                onTextChanged { _, _, _, _ ->

                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
