package com.yan.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yan.base.ext.onClick
import com.yan.base.ext.setVisible
import com.yan.base.ui.activity.BaseActivity
import com.yan.base.utils.AppPrefsUtils
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
import com.yan.goods.ui.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/15 18:50
 *  @description : 搜索商品列表页面
 */
class SearchGoodsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mAdapter: SearchHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mIvLeft.onClick(this)
        mTvSearch.onClick(this)
        mBtnClear.onClick(this)

        mAdapter = SearchHistoryAdapter(this)
        mAdapter.setOnItemClickListener { s, i ->
            enterGoodsList(s)
        }
        mRvSearchHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun loadData() {
        val set = AppPrefsUtils.getStringSet(GoodsConstant.SP_SEARCH_HISTORY)
        mTvNoData.setVisible(set.isEmpty())
        mDataView.setVisible(set.isNotEmpty())

        if (set.isNotEmpty()) {
            mAdapter.setData(set.toMutableList())
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mIvLeft -> finish()
            R.id.mTvSearch -> doSearch()
            R.id.mBtnClear -> {
                AppPrefsUtils.remove(GoodsConstant.SP_SEARCH_HISTORY)
                loadData()
            }
        }
    }

    private fun doSearch() {
        if (mEtKeyword.text.isNullOrEmpty()) {
            toast("请输入需要搜索的关键字")
        } else {
            val inputValue = mEtKeyword.text.toString()
            //先保存
            AppPrefsUtils.putStringSet(GoodsConstant.SP_SEARCH_HISTORY, mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }

    private fun enterGoodsList(item: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to item
        )
    }
}