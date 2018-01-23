package com.yan.goods.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.goods.data.protocol.Goods

/**
 *  @author      : yan
 *  @date        : 2018/1/17 17:50
 *  @description : 商品详情界面View层，负责回调结果
 */
interface GoodsDetailView : BaseView {

    fun onGetGoodsDetailResult(result: Goods)
    fun onAddCartResult(result: Int)
}