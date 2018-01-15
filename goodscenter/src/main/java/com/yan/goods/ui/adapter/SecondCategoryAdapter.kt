package com.yan.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.loadUrl
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.goods.R
import com.yan.goods.data.protocol.Category
import com.yan.goods.ui.adapter.SecondCategoryAdapter.SecondCategoryVH
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 11:17
 *  @description : 分类页面二级列表RecyclerView的adapter
 */
class SecondCategoryAdapter(context: Context)
    : BaseRecyclerViewAdapter<Category, SecondCategoryVH>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecondCategoryVH {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item, parent, false)
        return SecondCategoryVH(itemView)
    }

    override fun onBindViewHolder(holder: SecondCategoryVH, position: Int) {
        super.onBindViewHolder(holder, position)
        val category = dataList[position]
        holder.itemView.apply {
            mIvCategoryIcon.loadUrl(category.categoryIcon)
            mTvSecondCategoryName.text = category.categoryName
        }
    }

    class SecondCategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}