package com.yan.goods.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.goods.presenter.view.GoodsListView
import com.yan.goods.service.GoodsService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:03
 *  @description : 商品列表界面Presenter层
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        goodsService.getGoodsList(categoryId, pageNo)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetGoodsListResult(it) }
                }
    }
}