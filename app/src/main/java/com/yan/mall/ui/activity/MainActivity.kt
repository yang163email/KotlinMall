package com.yan.mall.ui.activity

import android.os.Bundle
import com.yan.base.ui.activity.BaseActivity
import com.yan.mall.R
import com.yan.mall.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 13:42
 *  @description : 主界面
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mFlContainer, HomeFragment())
                .commit()
    }
}
