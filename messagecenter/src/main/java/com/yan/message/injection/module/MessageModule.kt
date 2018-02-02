package com.yan.message.injection.module

import com.yan.message.service.MessageService
import com.yan.message.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:08
 *  @description : 提供消息相关字段的module
 */
@Module
class MessageModule {

    @Provides
    fun provideOrderService(service: MessageServiceImpl): MessageService = service
}