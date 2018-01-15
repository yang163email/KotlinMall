package com.yan.goods.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.loadUrl
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.goods.R
import com.yan.goods.data.protocol.Goods
import com.yan.goods.ui.adapter.GoodsAdapter.GoodsVH
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:13
 *  @description : 商品数据适配器
 */
class GoodsAdapter(context: Context) : BaseRecyclerViewAdapter<Goods, GoodsVH>(context) {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsVH {
       val view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_item, parent, false)
       return GoodsVH(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GoodsVH, position: Int) {
        super.onBindViewHolder(holder, position)
        val goods = dataList[position]
        holder.itemView.apply {
            //商品图标
            mIvGoodsIcon.loadUrl(goods.goodsDefaultIcon)
            //商品描述
            mTvGoodsDesc.text = goods.goodsDesc
            //商品价格
            mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(goods.goodsDefaultPrice)
            //商品销量及库存
            mTvGoodsSalesStock.text = "销量${goods.goodsSalesCount}件     库存${goods.goodsStockCount}"
        }
    }

    class GoodsVH(view: View) : RecyclerView.ViewHolder(view)
}
