package com.yan.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.base.widgets.BannerImageLoader
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
import com.yan.goods.data.protocol.Goods
import com.yan.goods.event.GoodsDetailImageEvent
import com.yan.goods.injection.component.DaggerGoodsComponent
import com.yan.goods.injection.module.GoodsModule
import com.yan.goods.presenter.GoodsDetailPresenter
import com.yan.goods.presenter.view.GoodsDetailView
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:53
 *  @description : 商品详情第一个tab页面
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        setBanner(result)
        mTvGoodsDesc.text = result.goodsDesc
        mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mTvSkuSelected.text = result.goodsDefaultSku
        //发送数据到详情Fragment
        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
    }

    private fun setBanner(goods: Goods) {
        //设置图片加载器
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
                //设置图片集合
                .setImages(goods.goodsBanner.split(","))
                //banner设置方法全部调用完毕时最后调用
                .start()
    }

}