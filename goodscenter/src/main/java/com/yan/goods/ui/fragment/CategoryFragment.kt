package com.yan.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.yan.base.ui.fragment.BaseMvpFragment
import com.yan.goods.R
import com.yan.goods.data.protocol.Category
import com.yan.goods.injection.component.DaggerCategoryComponent
import com.yan.goods.injection.module.CategoryModule
import com.yan.goods.presenter.CategoryPresenter
import com.yan.goods.presenter.view.CategoryView
import com.yan.goods.ui.adapter.SecondCategoryAdapter
import com.yan.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 10:32
 *  @description : 分类Fragment
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private lateinit var mTopAdapter: TopCategoryAdapter
    private lateinit var mSecondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(activityComponent)
                .categoryModule(CategoryModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mTopAdapter = TopCategoryAdapter(context)
        mTopAdapter.setOnItemClickListener { category, i ->
            // 如果已经选择，跳过
            if (mTopAdapter.dataList[i].isSelected) return@setOnItemClickListener
            mTopAdapter.dataList.forEach {
                //点击时将值修改
                it.isSelected = it.id == category.id
            }
            mTopAdapter.notifyDataSetChanged()
            //加载数据
            loadData(category.id)
        }
        mRvTopCategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mTopAdapter
        }

        mSecondAdapter = SecondCategoryAdapter(context)
        mSecondAdapter.setOnItemClickListener { category, i ->
            TODO()
        }
        mRvSecondCategory.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = mSecondAdapter
        }
    }

    private fun loadData(parentId: Int = 0) {
        mPresenter.getCategory(parentId)
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                mTopAdapter.setData(result)
                loadData(result[0].id)
            } else {
                mSecondAdapter.setData(result)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
        }
    }
}