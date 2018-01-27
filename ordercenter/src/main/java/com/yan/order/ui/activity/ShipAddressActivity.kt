package com.yan.order.ui.activity

import android.os.Bundle
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseActivity
import com.yan.order.R
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:31
 *  @description : 收货地址界面
 */
class ShipAddressActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    private fun initView() {
        mBtnAddAddress.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }
}