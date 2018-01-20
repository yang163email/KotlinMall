package com.yan.goods.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yan.goods.ui.fragment.GoodsDetailTabOneFragment
import com.yan.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 *  @author      : yan
 *  @date        : 2018/1/17 17:18
 *  @description : 商品详情ViewPager适配器
 */
class GoodsDetailVpAdapter(fm: FragmentManager?, val context: Context) : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) GoodsDetailTabOneFragment()
        else GoodsDetailTabTwoFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}