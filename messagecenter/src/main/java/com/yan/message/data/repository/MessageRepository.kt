package com.yan.message.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.message.data.api.MessageApi
import com.yan.message.data.protocol.Message
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/2/2 9:54
 *  @description : 消息数据层
 */
class MessageRepository @Inject constructor() {

    /**
     * 获取消息列表
     */
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>> =
            RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()

}
