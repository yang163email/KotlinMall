package com.yan.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.onClick
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.order.R
import com.yan.order.data.protocol.ShipAddress
import com.yan.order.ui.adapter.ShipAddressAdapter.ViewHolder
import kotlinx.android.synthetic.main.layout_address_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/1/29 10:36
 *  @description : 收货地址数据适配
 */
class ShipAddressAdapter(context: Context) :
        BaseRecyclerViewAdapter<ShipAddress, ViewHolder>(context) {

    var mOptClickListener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.apply {
            mTvSetDefault.isSelected = (model.shipIsDefault == 0)
            mTvShipName.text = model.shipUserName + "    " + model.shipUserMobile
            mTvShipAddress.text = model.shipAddress

            mTvSetDefault.onClick {
                mOptClickListener?.let {
                    if (mTvSetDefault.isSelected) return@onClick
                    model.shipIsDefault = 0
                    it.onSetDefault(model)
                }
            }
            mTvEdit.onClick {
                mOptClickListener?.onEdit(model)
            }
            mTvDelete.onClick {
                mOptClickListener?.onDelete(model)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /**
     * 对应操作接口
     */
    interface OnOptClickListener {
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address: ShipAddress)
        fun onDelete(address: ShipAddress)
    }
}
