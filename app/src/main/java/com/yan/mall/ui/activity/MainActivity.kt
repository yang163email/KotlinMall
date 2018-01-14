package com.yan.mall.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.yan.base.ui.activity.BaseActivity
import com.yan.mall.R
import com.yan.mall.ui.fragment.HomeFragment
import com.yan.mall.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 13:42
 *  @description : 主界面
 */
class MainActivity : BaseActivity() {

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMineFragment by lazy { MineFragment() }

    private val mStack: Stack<Fragment> = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        initFragment()
        initBottomNavBar()
        changeFragment(0)
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mFlContainer, mHomeFragment)
                .add(R.id.mFlContainer, mCategoryFragment)
                .add(R.id.mFlContainer, mCartFragment)
                .add(R.id.mFlContainer, mMsgFragment)
                .add(R.id.mFlContainer, mMineFragment)
                .commit()

        //将Fragment加入栈中
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMineFragment)
    }

    private fun initBottomNavBar() {
        mBottomNavBar.setTabSelectedListener(
                object : BottomNavigationBar.SimpleOnTabSelectedListener() {
                    override fun onTabSelected(position: Int) {
                        changeFragment(position)
                    }
                })
    }

    /**
     * 切换Tab，切换到对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        mStack.forEach { ft.hide(it) }
        ft.show(mStack[position])
        ft.commit()
    }
}
