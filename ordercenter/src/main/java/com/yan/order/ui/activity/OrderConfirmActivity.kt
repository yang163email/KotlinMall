package com.yan.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.data.protocol.Order
import com.yan.order.injection.component.DaggerOrderComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.presenter.OrderConfirmPresenter
import com.yan.order.presenter.view.OrderConfirmView
import com.yan.order.ui.adapter.OrderGoodsAdapter
import com.yan.provider.common.ProviderConstant
import com.yan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : yan
 *  @date        : 2018/1/27 12:48
 *  @description : 订单确认页面
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        ARouter.getInstance().inject(this)
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
        mTvSelectShip.onClick {
            startActivity<ShipAddressActivity>()
        }
        mAdapter = OrderGoodsAdapter(this)
        mRvOrderGoods.apply {
            layoutManager = LinearLayoutManager(this@OrderConfirmActivity)
            adapter = mAdapter
        }
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mAdapter.setData(result.orderGoodsList)
        mTvTotalPrice.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
    }
}