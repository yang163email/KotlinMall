package com.yan.message.service

import com.yan.message.data.protocol.Message
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:00
 *  @description : 消息业务接口
 */
interface MessageService {
    //获取消息列表
    fun getMessageList(): Observable<MutableList<Message>?>

}
