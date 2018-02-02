package com.yan.message.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.message.data.protocol.Message

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:04
 *  @description : 消息页面回调层
 */
interface MessageView : BaseView {

    fun onGetMessageResult(result: MutableList<Message>?)
}