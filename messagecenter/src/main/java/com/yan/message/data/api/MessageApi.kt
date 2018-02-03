package com.yan.message.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.message.data.protocol.Message
import retrofit2.http.POST
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/2/2 9:55
 *  @description : 消息 接口
 */
interface MessageApi {

    /**
     * 获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
