package com.yan.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.data.protocol.Order
import com.yan.order.injection.component.DaggerOrderComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.presenter.OrderDetailPresenter
import com.yan.order.presenter.view.OrderDetailView
import com.yan.order.ui.adapter.OrderGoodsAdapter
import com.yan.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 *  @author      : yan
 *  @date        : 2018/1/30 20:23
 *  @description : 订单详情页面
 */
class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
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
        mAdapter = OrderGoodsAdapter(this)
        mRvOrderGoods.apply {
            layoutManager = LinearLayoutManager(this@OrderDetailActivity)
            adapter = mAdapter
        }
    }

    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1))
    }

    override fun onGetOrderByIdResult(result: Order) {
        mAdapter.setData(result.orderGoodsList)
        result.shipAddress?.apply {
            mTvShipName.setContentText(shipUserName)
            mTvShipMobile.setContentText(shipUserMobile)
            mTvShipAddress.setContentText(shipAddress)
            mTvTotalPrice.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))
        }
    }

}