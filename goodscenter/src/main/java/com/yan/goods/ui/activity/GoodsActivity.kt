package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.yan.base.ext.startLoading
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
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
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView,
        BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var mGoodsAdapter: GoodsAdapter

    private var mCurrentPage = 1
    private var mMaxPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout();
        loadData()
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mGoodsAdapter = GoodsAdapter(this)
        mRvGoods.apply {
            layoutManager = GridLayoutManager(this@GoodsActivity, 2)
            adapter = mGoodsAdapter
        }
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val holder = BGANormalRefreshViewHolder(this, true)
        // 设置整个加载更多控件的背景颜色资源 id
        holder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        // 设置下拉刷新控件的背景颜色资源 id
        holder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(holder)
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            //进入搜索
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endRefreshing()
        mRefreshLayout.endLoadingMore()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage < mMaxPage) {
            mCurrentPage ++
            loadData()
            true
        } else false
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

}
