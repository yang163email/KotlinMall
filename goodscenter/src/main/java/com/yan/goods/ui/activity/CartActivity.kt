package com.yan.goods.ui.activity

import android.os.Bundle
import com.yan.base.ui.activity.BaseActivity
import com.yan.goods.R
import com.yan.goods.ui.fragment.CartFragment

/**
 *  @author      : yan
 *  @date        : 2018/1/24 13:46
 *  @description : 购物车Activity
 */
class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val cartFragment = supportFragmentManager.findFragmentById(R.id.fragment_cart) as CartFragment
        cartFragment.setBackVisible(true)
    }
}