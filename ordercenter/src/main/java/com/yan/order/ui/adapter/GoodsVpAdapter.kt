package com.yan.order.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yan.order.common.OrderConstant
import com.yan.order.ui.fragment.OrderFragment

/**
 *  @author      : yan
 *  @date        : 2018/1/29 18:57
 *  @description : 订单ViewPager适配器
 */
class GoodsVpAdapter(fm: FragmentManager?, val context: Context) : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部", "待付款", "待收货", "已完成", "已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS, position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}