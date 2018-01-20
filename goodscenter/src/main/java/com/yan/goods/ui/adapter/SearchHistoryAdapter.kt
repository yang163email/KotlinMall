package com.yan.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.goods.R
import com.yan.goods.ui.adapter.SearchHistoryAdapter.SearchHistoryVH
import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 19:22
 *  @description : 搜索历史RecyclerView的adapter
 */
class SearchHistoryAdapter(context: Context) : BaseRecyclerViewAdapter<String, SearchHistoryVH>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchHistoryVH {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_search_history_item, parent, false)
        return SearchHistoryVH(itemView)
    }

    override fun onBindViewHolder(holder: SearchHistoryVH, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.mTvSearchHistory.text = dataList[position]
    }

    class SearchHistoryVH(itemView: View) : RecyclerView.ViewHolder(itemView)
}