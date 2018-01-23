package com.yan.goods.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.goods.presenter.view.CartListView
import com.yan.goods.service.CartService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/23 15:30
 *  @description : 购物车列表界面Presenter层
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    fun getCartList() {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        cartService.getCartList().execute2(lifecycleProvider, mView) {
            onNext {
                mView.onGetGetCartListResult(it)
            }
        }
    }
}