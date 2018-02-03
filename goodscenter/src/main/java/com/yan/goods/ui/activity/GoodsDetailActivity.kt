package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.View
import com.yan.base.utils.Bus
import com.yan.base.utils.registerInBus
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseActivity
import com.yan.base.utils.AppPrefsUtils
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
import com.yan.goods.event.AddCartEvent
import com.yan.goods.event.UpdateCartSizeEvent
import com.yan.goods.ui.adapter.GoodsDetailVpAdapter
import com.yan.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:39
 *  @description : 商品详情页面
 */
class GoodsDetailActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initListener()
        initObserve()
        initCartBadge()
        loadCartSize()
    }

    private fun initCartBadge() {
        mCartBadge = QBadgeView(this)
        mCartBadge.setGravityOffset(22f, -2f, true)
                .setBadgeTextSize(6f, true)
                .bindTarget(mTvEnterCart)
                .badgeGravity = Gravity.END or Gravity.TOP
    }

    private fun initListener() {
        mIvLeft.onClick(this)
        mBtnAddCart.onClick(this)
        mTvEnterCart.onClick(this)
    }

    private fun initView() {
        mTlGoodsDetail.tabMode = TabLayout.MODE_FIXED
        mVpGoodsDetail.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mTlGoodsDetail.setupWithViewPager(mVpGoodsDetail)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    loadCartSize()
                }.registerInBus(this)
    }

    private fun loadCartSize() {
        mCartBadge.badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mIvLeft -> finish()
            R.id.mBtnAddCart -> {
                afterLogin {
                    Bus.send(AddCartEvent())
                }
            }
            R.id.mTvEnterCart -> startActivity<CartActivity>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}