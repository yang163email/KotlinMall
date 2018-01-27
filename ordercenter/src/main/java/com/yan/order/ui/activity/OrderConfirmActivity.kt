package com.yan.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.data.protocol.Order
import com.yan.order.injection.component.DaggerOrderComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.presenter.OrderConfirmPresenter
import com.yan.order.presenter.view.OrderConfirmView
import com.yan.provider.common.ProviderConstant
import com.yan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*

/**
 *  @author      : yan
 *  @date        : 2018/1/27 12:48
 *  @description : 订单确认页面
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    private var mOrderId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
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
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mTvTotalPrice.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
    }
}