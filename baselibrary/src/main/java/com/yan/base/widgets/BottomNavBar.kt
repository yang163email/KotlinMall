package com.yan.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.yan.base.R

/**
 *  @author      : yan
 *  @date        : 2018/1/14 13:43
 *  @description : 封装Google提供的BottomNavigationBar
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    /** 购物车标签 */
    private var mCartBadge: TextBadgeItem
    /** 消息标签 */
    private var mMsgBadge: ShapeBadgeItem

    init {
        //首页
        val homeItem = BottomNavigationItem(
                R.drawable.btn_nav_home_press, R.string.nav_bar_home)
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .initNavTextColor()
        //分类
        val categoryItem = BottomNavigationItem(
                R.drawable.btn_nav_category_press, R.string.nav_bar_category)
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .initNavTextColor()
        //购物车
        val cartItem = BottomNavigationItem(
                R.drawable.btn_nav_cart_press, R.string.nav_bar_cart)
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .initNavTextColor()
        mCartBadge = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)

        //消息
        val msgItem = BottomNavigationItem(
                R.drawable.btn_nav_msg_press, R.string.nav_bar_msg)
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .initNavTextColor()
        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadge)

        //我的
        val userItem = BottomNavigationItem(
                R.drawable.btn_nav_user_press, R.string.nav_bar_user)
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .initNavTextColor()

        // 设置底部导航栏模式及样式
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItems(homeItem, categoryItem, cartItem, msgItem, userItem)
        setFirstSelectedPosition(0)
        initialise()
    }

    /**
     * 检查购物车Tab是否显示标签
     */
    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }

    /**
     * 检查消息Tab是否显示标签
     */
    fun checkMsgBadge(isShow: Boolean) {
        mMsgBadge.apply { if (isShow) show() else hide() }
    }

    /**
     * 扩展，设置了底部文字的颜色
     */
    private fun BottomNavigationItem.initNavTextColor(): BottomNavigationItem =
            setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

    private fun addItems(vararg items: BottomNavigationItem) =
        items.forEach { addItem(it) }
}