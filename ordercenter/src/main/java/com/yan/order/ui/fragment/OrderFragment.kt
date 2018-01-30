package com.yan.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.yan.base.ext.startLoading
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.common.OrderStatus
import com.yan.order.data.protocol.Order
import com.yan.order.injection.component.DaggerOrderComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.presenter.OrderListPresenter
import com.yan.order.presenter.view.OrderListView
import com.yan.order.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/29 19:00
 *  @description : 订单Fragment
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(activityComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mAdapter = OrderAdapter(activity)
        mAdapter.onOptClickListener = { type, order ->
            when (type) {
                OrderConstant.OPT_ORDER_PAY -> toast("待支付")
                OrderConstant.OPT_ORDER_CONFIRM -> toast("确认支付")
                OrderConstant.OPT_ORDER_CANCEL -> toast("取消")
            }
        }
        mRvOrder.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderById(arguments.getInt(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL))
    }

    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}