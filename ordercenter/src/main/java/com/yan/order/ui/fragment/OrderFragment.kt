package com.yan.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.alertview.AlertView
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
import com.yan.order.ui.activity.OrderDetailActivity
import com.yan.order.ui.adapter.OrderAdapter
import com.yan.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
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
                OrderConstant.OPT_ORDER_CONFIRM -> mPresenter.confirmOrder(order.id)
                OrderConstant.OPT_ORDER_CANCEL -> cancelOrder(order)
            }
        }
        mAdapter.setOnItemClickListener { order, i ->
            startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to order.id)
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

    private fun cancelOrder(order: Order) {
        AlertView.Builder()
                .setContext(activity)
                .setStyle(AlertView.Style.Alert)
                .setTitle("删除")
                .setMessage("确认删除改地址？")
                .setCancelText("取消")
                .setOthers(arrayOf("确定"))
                .setOnItemClickListener { _, position ->
                    if (position == 0)
                        mPresenter.cancelOrder(order.id)
                }
                .build()
                .setCancelable(true)
                .show()
    }

    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认订单成功")
        loadData()
    }

    override fun onCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }

}