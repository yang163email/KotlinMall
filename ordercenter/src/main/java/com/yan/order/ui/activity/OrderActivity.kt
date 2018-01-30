package com.yan.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.yan.base.ui.activity.BaseActivity
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.common.OrderStatus
import com.yan.order.ui.adapter.GoodsVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 *  @author      : yan
 *  @date        : 2018/1/29 18:54
 *  @description : 订单页面
 */
class OrderActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {
        mVpOrder.adapter = GoodsVpAdapter(supportFragmentManager, this)
        mVpOrder.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
        mTabOrder.tabMode = TabLayout.MODE_FIXED
        mTabOrder.setupWithViewPager(mVpOrder)
    }
}