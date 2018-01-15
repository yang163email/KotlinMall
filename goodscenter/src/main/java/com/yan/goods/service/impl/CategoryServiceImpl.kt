package com.yan.goods.service.impl

import com.yan.base.ext.convert
import com.yan.goods.data.protocol.Category
import com.yan.goods.data.repository.CategoryRepository
import com.yan.goods.service.CategoryService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:46
 *  @description : 用户Service实现类，调用数据仓库进行网络请求
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> =
            repository.getCategory(parentId).convert()

}