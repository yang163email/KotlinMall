package com.yan.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.onClick
import com.yan.base.ext.setVisible
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.base.utils.Bus
import com.yan.base.utils.registerInBus
import com.yan.order.R
import com.yan.order.data.protocol.Order
import com.yan.order.event.SelectAddressEvent
import com.yan.order.injection.component.DaggerOrderComponent
import com.yan.order.injection.module.OrderModule
import com.yan.order.presenter.OrderConfirmPresenter
import com.yan.order.presenter.view.OrderConfirmView
import com.yan.order.ui.adapter.OrderGoodsAdapter
import com.yan.provider.common.ProviderConstant
import com.yan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/27 12:48
 *  @description : 订单确认页面
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView, View.OnClickListener {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0
    private var mCurrentOrder: Order? = null

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        initView()
        initObserve()
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
        mTvSelectShip.onClick(this)
        mShipView.onClick(this)
        mBtnSubmitOrder.onClick(this)

        mAdapter = OrderGoodsAdapter(this)
        mRvOrderGoods.apply {
            layoutManager = LinearLayoutManager(this@OrderConfirmActivity)
            adapter = mAdapter
        }
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe { event ->
                    mCurrentOrder?.let { it.shipAddress = event.address }
                    updateAddressView()
                }.registerInBus(this)
    }

    private fun updateAddressView() {
        mCurrentOrder?.shipAddress?.let {
            mTvSelectShip.setVisible(false)
            mShipView.setVisible(true)

            mTvShipName.text = it.shipUserName + "  " + it.shipUserMobile
            mTvShipAddress.text = it.shipAddress
        } ?: run {
            mTvSelectShip.setVisible(true)
            mShipView.setVisible(false)
        }
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTvTotalPrice.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    override fun onSubmitOrderResult(result: Boolean) {
        toast("提交订单成功")
        mCurrentOrder?.let {
            ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                    .withInt(ProviderConstant.KEY_ORDER_ID, it.id)
                    .withLong(ProviderConstant.KEY_ORDER_PRICE, it.totalPrice)
                    .navigation()
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mTvSelectShip, R.id.mShipView -> startActivity<ShipAddressActivity>()
            R.id.mBtnSubmitOrder -> mCurrentOrder?.let { mPresenter.submitOrder(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}