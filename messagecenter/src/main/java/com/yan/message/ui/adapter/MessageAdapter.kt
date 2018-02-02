package com.yan.message.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.loadUrl
import com.yan.base.ui.adapter.BaseRecyclerViewAdapter
import com.yan.message.R
import com.yan.message.data.protocol.Message
import com.yan.message.ui.adapter.MessageAdapter.ViewHolder
import kotlinx.android.synthetic.main.layout_message_item.view.*

/**
 *  @author      : yan
 *  @date        : 2018/2/2 9:52
 *  @description : 消息数据
 */
class MessageAdapter(context: Context) : BaseRecyclerViewAdapter<Message, ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.apply {
            //消息图标
            mIvMsgIcon.loadUrl(model.msgIcon)
            //消息标题
            mTvMsgTitle.text = model.msgTitle
            //消息内容
            mTvMsgContent.text = model.msgContent
            //消息时间
            mTvMsgTime.text = model.msgTime
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
