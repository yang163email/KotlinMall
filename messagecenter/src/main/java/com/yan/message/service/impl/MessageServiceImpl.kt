package com.yan.message.service.impl

import com.yan.base.ext.convert
import com.yan.message.data.protocol.Message
import com.yan.message.data.repository.MessageRepository
import com.yan.message.service.MessageService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:00
 *  @description : 消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /**
     * 获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> =
            repository.getMessageList().convert()

}
