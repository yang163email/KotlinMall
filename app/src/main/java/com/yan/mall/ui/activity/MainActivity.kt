package com.yan.mall.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.yan.base.utils.Bus
import com.yan.base.utils.registerInBus
import com.yan.base.common.AppManager
import com.yan.base.ui.activity.BaseActivity
import com.yan.base.utils.AppPrefsUtils
import com.yan.goods.common.GoodsConstant
import com.yan.goods.event.UpdateCartSizeEvent
import com.yan.goods.ui.fragment.CartFragment
import com.yan.goods.ui.fragment.CategoryFragment
import com.yan.mall.R
import com.yan.mall.ui.fragment.HomeFragment
import com.yan.mall.ui.fragment.MineFragment
import com.yan.message.ui.fragment.MessageFragment
import com.yan.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 13:42
 *  @description : 主界面
 */
class MainActivity : BaseActivity() {

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMineFragment by lazy { MineFragment() }

    private val mStack: Stack<Fragment> = Stack()

    /** 按下back键的时间，默认0 */
    private var pressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNavBar()
        changeFragment(0)
        initObserve()
        loadCartSize()
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
        mBottomNavBar.checkMsgBadge(false)
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

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    loadCartSize()
                }.registerInBus(this)
        Bus.observe<MessageBadgeEvent>()
                .subscribe {
                    mBottomNavBar.checkMsgBadge(it.isVisible)
                }.registerInBus(this)
    }

    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出")
            pressTime = time
        } else AppManager.instance.exitApp(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
