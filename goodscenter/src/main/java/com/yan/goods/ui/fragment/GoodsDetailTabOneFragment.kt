package com.yan.goods.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseActivity
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.base.widgets.BannerImageLoader
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
import com.yan.goods.data.protocol.Goods
import com.yan.goods.event.AddCartEvent
import com.yan.goods.event.GoodsDetailImageEvent
import com.yan.goods.event.SkuChangedEvent
import com.yan.goods.injection.component.DaggerGoodsComponent
import com.yan.goods.injection.module.GoodsModule
import com.yan.goods.presenter.GoodsDetailPresenter
import com.yan.goods.presenter.view.GoodsDetailView
import com.yan.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/17 16:53
 *  @description : 商品详情第一个tab页面
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView

    private var mCurrGoods: Goods? = null

    private lateinit var mAnimationStart: ScaleAnimation
    private lateinit var mAnimationEnd: ScaleAnimation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        initObserve()
        loadData()
    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        //设置图片加载器
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
                //设置banner动画效果
                .setBannerAnimation(Transformer.Accordion)
                //设置轮播时间
                .setDelayTime(2000)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {
            mSkuPop.showAsDropDown(
                    (activity as BaseActivity).contentView,
                    Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0
            )
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    /**
     * 初始化缩放动画
     */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f,
                1f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f,
                0.95f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mTvSkuSelected.text = mSkuPop.getSelectSku() +
                            GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mCurrGoods = result
        setBanner(result)
        mTvGoodsDesc.text = result.goodsDesc
        mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mTvSkuSelected.text = result.goodsDefaultSku
        //发送数据到详情Fragment
        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
        //加载数据到popupWindow上面
        loadPopData(result)
    }

    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)
    }

    private fun setBanner(goods: Goods) {
        //设置图片集合
        mGoodsDetailBanner.setImages(goods.goodsBanner.split(","))
                //banner设置方法全部调用完毕时最后调用
                .start()
    }

    /**
     * 添加到购物车
     */
    private fun addCart() {
        mCurrGoods?.let {
            mPresenter.addCart(it.id, it.goodsDesc, it.goodsDefaultIcon,
                    it.goodsDefaultPrice, mSkuPop.getSelectCount(), mSkuPop.getSelectSku())
        }
    }

    override fun onAddCartResult(result: Int) {
        toast("Cart----$result")
    }
}