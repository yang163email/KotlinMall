package com.yan.goods.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.goods.data.protocol.CartGoods

/**
 *  @author      : yan
 *  @date        : 2018/1/23 15:32
 *  @description : 购物车列表界面View层，负责回调结果
 */
interface CartListView : BaseView {

    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
}