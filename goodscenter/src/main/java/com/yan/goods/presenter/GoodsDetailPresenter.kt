package com.yan.goods.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.goods.presenter.view.GoodsDetailView
import com.yan.goods.service.CartService
import com.yan.goods.service.GoodsService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/17 17:48
 *  @description : 商品详情界面Presenter层
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    fun getGoodsDetail(goodsId: Int) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        goodsService.getGoodsDetail(goodsId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetGoodsDetailResult(it) }
                }
    }

    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onAddCartResult(it) }
                }
    }
}