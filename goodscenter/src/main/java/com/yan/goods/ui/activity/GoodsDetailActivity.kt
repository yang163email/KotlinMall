package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.yan.base.ui.activity.BaseActivity
import com.yan.goods.R
import com.yan.goods.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:39
 *  @description : 商品详情页面
 */
class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mTlGoodsDetail.tabMode = TabLayout.MODE_FIXED
        mVpGoodsDetail.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mTlGoodsDetail.setupWithViewPager(mVpGoodsDetail)
    }
}