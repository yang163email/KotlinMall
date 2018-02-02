package com.yan.mall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.onClick
import com.yan.base.ui.fragment.BaseFragment
import com.yan.base.widgets.BannerImageLoader
import com.yan.goods.ui.activity.SearchGoodsActivity
import com.yan.mall.R
import com.yan.mall.common.*
import com.yan.mall.ui.adapter.HomeDiscountAdapter
import com.yan.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/14 14:56
 *  @description : 首页Fragment
 */
class HomeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initListener() {
        mIvScan.onClick(this)
        mEtSearch.onClick(this)
    }

    private fun initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(BannerImageLoader())
                //设置图片集合
                .setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
                //设置banner动画效果
                .setBannerAnimation(Transformer.Accordion)
                //设置轮播时间
                .setDelayTime(2000)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.CENTER)
                //banner设置方法全部调用完毕时最后调用
                .start()
    }

    /**
     * 初始化公告
     */
    private fun initNews() {
        //公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    /**
     * 初始化折扣
     */
    private fun initDiscount() {
        val discountAdapter = HomeDiscountAdapter(context)
        mRvHomeDiscount.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = discountAdapter
        }
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO,
                HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))

        discountAdapter.setOnItemClickListener { s, i ->
            println("position: $i, content: $s")
        }

    }

    /**
     * 初始化主题
     */
    private fun initTopic() {
        mVpTopic.apply {
            adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO,
                    HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
            currentItem = 1
            offscreenPageLimit = 5

            CoverFlow.Builder()
                    .with(this)
                    .scale(0.3f)
                    .pagerMargin(-30.0f)
                    .spaceSize(0.0f)
                    .build()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mEtSearch -> startActivity<SearchGoodsActivity>()
            R.id.mIvScan -> toast(R.string.coming_soon_tip)
        }
    }
}
