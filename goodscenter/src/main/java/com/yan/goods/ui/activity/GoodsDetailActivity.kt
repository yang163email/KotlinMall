package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.eightbitlab.rxbus.Bus
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseActivity
import com.yan.goods.R
import com.yan.goods.event.AddCartEvent
import com.yan.goods.ui.adapter.GoodsDetailVpAdapter
import com.yan.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:39
 *  @description : 商品详情页面
 */
class GoodsDetailActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initListener()
    }

    private fun initListener() {
        mBtnAddCart.onClick(this)
    }

    private fun initView() {
        mTlGoodsDetail.tabMode = TabLayout.MODE_FIXED
        mVpGoodsDetail.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mTlGoodsDetail.setupWithViewPager(mVpGoodsDetail)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mBtnAddCart -> {
                afterLogin {
                    Bus.send(AddCartEvent())
                }
            }
        }
    }
}