package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.yan.base.ext.startLoading
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.goods.R
import com.yan.goods.data.protocol.Goods
import com.yan.goods.injection.component.DaggerGoodsComponent
import com.yan.goods.injection.module.GoodsModule
import com.yan.goods.presenter.GoodsListPresenter
import com.yan.goods.presenter.view.GoodsListView
import com.yan.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 16:02
 *  @description : 商品列表页面
 */
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView {

    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        loadData()
    }

    private fun initView() {
        mGoodsAdapter = GoodsAdapter(this)
        mRvGoods.apply {
            layoutManager = GridLayoutManager(this@GoodsActivity, 2)
            adapter = mGoodsAdapter
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), 1)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        if (result != null && result.size > 0) {
            mGoodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }
    }

}
