package com.yan.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.fragment.BaseFragment
import com.yan.goods.R

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:53
 *  @description : 商品详情第一个tab页面
 */
class GoodsDetailTabOneFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }
}