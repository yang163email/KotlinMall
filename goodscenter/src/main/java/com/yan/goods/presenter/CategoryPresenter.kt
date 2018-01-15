package com.yan.goods.presenter

import com.yan.base.ext.execute2
import com.yan.base.presenter.BasePresenter
import com.yan.goods.presenter.view.CategoryView
import com.yan.goods.service.CategoryService
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:13
 *  @description : 登录界面Presenter层
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int) {
        //业务逻辑
        if (!checkNetWork()) return
        mView.showLoading()

        categoryService.getCategory(parentId)
                .execute2(lifecycleProvider, mView) {
                    onNext { mView.onGetCategoryResult(it) }
                }
    }
}