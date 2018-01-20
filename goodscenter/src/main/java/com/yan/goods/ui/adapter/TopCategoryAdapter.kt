package com.yan.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.goods.R
import com.yan.goods.data.protocol.Category
import com.yan.goods.ui.adapter.TopCategoryAdapter.TopCategoryVH
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 11:00
 *  @description : 分类页面一级列表RecyclerView的adapter
 */
class TopCategoryAdapter(context: Context)
    : BaseRecyclerViewAdapter<Category, TopCategoryVH>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TopCategoryVH {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item, parent, false)
        return TopCategoryVH(itemView)
    }

    override fun onBindViewHolder(holder: TopCategoryVH, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]
        holder.itemView.apply {
            mTvTopCategoryName.text = category.categoryName
            //是否选中显示
            mTvTopCategoryName.isSelected = category.isSelected
        }
    }

    class TopCategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}