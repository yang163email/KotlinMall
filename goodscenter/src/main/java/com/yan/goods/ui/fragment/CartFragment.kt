package com.yan.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.onClick2
import com.yan.base.ext.setVisible
import com.yan.base.ext.startLoading
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.goods.R
import com.yan.goods.data.protocol.CartGoods
import com.yan.goods.event.CartAllCheckedEvent
import com.yan.goods.event.UpdateTotalPriceEvent
import com.yan.goods.injection.component.DaggerCartComponent
import com.yan.goods.injection.module.CartModule
import com.yan.goods.presenter.CartListPresenter
import com.yan.goods.presenter.view.CartListView
import com.yan.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 *  @author      : yan
 *  @date        : 2018/1/23 14:28
 *  @description : 购物车Fragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
        loadData()
    }

    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(activityComponent)
                .cartModule(CartModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mAdapter = CartGoodsAdapter(context)
        mRvCartGoods.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        mCbAllChecked.onClick2 { cb ->
            mAdapter.dataList.forEach { it.isSelected = cb.isChecked }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
                    mCbAllChecked.isChecked = it.isAllChecked
                    updateTotalPrice()
                }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }.registerInBus(this)
    }

    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList.asSequence()
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTvTotalPrice.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun onGetGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mCbAllChecked.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Bus.unregister(this)
    }
}