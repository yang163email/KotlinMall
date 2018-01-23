package com.yan.goods.presenter

import com.eightbitlab.rxbus.Bus
import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.base.utils.AppPrefsUtils
import com.yan.goods.common.GoodsConstant
import com.yan.goods.event.UpdateCartSizeEvent
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
                //先保存，再通知更新
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, it?.size ?: 0)
                Bus.send(UpdateCartSizeEvent())
                mView.onGetCartListResult(it)
            }
        }
    }

    fun deleteCartList(cartIdList: List<Int>) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        cartService.deleteCartList(cartIdList).execute2(lifecycleProvider, mView) {
            onNext {
                mView.onDeleteCartListResult(it)
            }
        }
    }
}