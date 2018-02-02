package com.yan.message.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.message.presenter.view.MessageView
import com.yan.message.service.MessageService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:04
 *  @description : 消息页面P层
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    fun getMessageList() {
        if (!checkNetWork()) return
        mView.showLoading()

        messageService.getMessageList()
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetMessageResult(it) }
                }
    }

}