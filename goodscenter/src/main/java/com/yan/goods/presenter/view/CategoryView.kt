package com.yan.goods.presenter.view

import com.yan.base.presenter.view.BaseView
import com.yan.goods.data.protocol.Category

/**
 *  @author      : yan
 *  @date        : 2018/1/15 12:53
 *  @description : 商品分类界面View层，负责回调结果
 */
interface CategoryView : BaseView {

    fun onGetCategoryResult(result: MutableList<Category>?)
}