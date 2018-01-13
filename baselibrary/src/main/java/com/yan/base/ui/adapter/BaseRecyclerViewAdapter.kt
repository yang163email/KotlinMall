package com.kotlin.base.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.yan.base.alias.T1T2Unit
import com.yan.base.ext.onClick

/**
 *  @author      : yan
 *  @date        : 2018/1/13 15:13
 *  @description : RecyclerViewAdapter基类
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(val mContext: Context)
    : RecyclerView.Adapter<VH>() {

    //ItemClick事件
//    var mItemClickListener: OnItemClickListener<T>? = null
    private var mItemClickListener: T1T2Unit<T, Int>? = null

    //数据集合
    private var dataList: MutableList<T> = mutableListOf()

    /**
     * 设置数据
     * Presenter处理过为null的情况，所以为不会为Null
     */
    fun setData(sources: MutableList<T>) {
        dataList = sources
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.onClick {
            mItemClickListener?.invoke(dataList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

//    /**
//     * ItemClick事件声明
//     */
//    interface OnItemClickListener<in T> {
//        fun onItemClick(item: T, position: Int)
//    }

    fun setOnItemClickListener(listener: T1T2Unit<T, Int>) {
        this.mItemClickListener = listener
    }
}
