package com.yan.goods.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.goods.data.protocol.Goods

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:04
 *  @description : 商品列表界面View层，负责回调结果
 */
interface GoodsListView : BaseView {

    fun onGetGoodsListResult(result: MutableList<Goods>?)
}