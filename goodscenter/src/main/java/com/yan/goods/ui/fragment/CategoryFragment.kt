package com.yan.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.fragment.BaseFragment
import com.yan.goods.R
import com.yan.goods.ui.adapter.SecondCategoryAdapter
import com.yan.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 *  @author      : yan
 *  @date        : 2018/1/15 10:32
 *  @description : 分类Fragment
 */
class CategoryFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_category, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val topAdapter = TopCategoryAdapter(context)
        topAdapter.setOnItemClickListener { category, i ->
            //点击时将值修改
            topAdapter.dataList[i].isSelected = true
//            topAdapter.dataList.forEach {
//                //点击时将值修改
//                category.isSelected = it.id == category.id
//            }
            topAdapter.notifyDataSetChanged()
        }
        mRvTopCategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = topAdapter
        }

        val secondAdapter = SecondCategoryAdapter(context)
        secondAdapter.setOnItemClickListener { category, i ->
            TODO()
        }
        mRvSecondCategory.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = secondAdapter
        }

    }
}