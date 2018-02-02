package com.yan.message.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.yan.base.ext.startLoading
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.message.R
import com.yan.message.data.protocol.Message
import com.yan.message.injection.component.DaggerMessageComponent
import com.yan.message.injection.module.MessageModule
import com.yan.message.presenter.MessagePresenter
import com.yan.message.presenter.view.MessageView
import com.yan.message.ui.adapter.MessageAdapter
import com.yan.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.fragment_message.*

/**
 *  @author      : yan
 *  @date        : 2018/2/2 10:01
 *  @description : 消息fragment
 */
class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageView {

    private lateinit var mAdapter: MessageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun injectComponent() {
        DaggerMessageComponent.builder()
                .activityComponent(activityComponent)
                .messageModule(MessageModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()
    }

    private fun initView() {
        mAdapter = MessageAdapter(activity)
        mRvMessage.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onGetMessageResult(result: MutableList<Message>?) {
        if (result != null && result.isNotEmpty()) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            Bus.send(MessageBadgeEvent(false))
        }
    }
}