package com.yan.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yan.base.ext.loadUrl
import com.yan.base.ui.fragment.BaseFragment
import com.yan.goods.R
import com.yan.goods.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import rx.android.schedulers.AndroidSchedulers

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:57
 *  @description : 商品详情第二个tab页面
 */
class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mIvGoodsDetailOne.loadUrl(it.image1)
                    mIvGoodsDetailTwo.loadUrl(it.image2)
                }
                .registerInBus(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Bus.unregister(this)
    }
}