package com.yan.goods.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.goods.data.api.CategoryApi
import com.yan.goods.data.protocol.Category
import com.yan.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/15 12:49
 *  @description : 分类仓库类，Retrofit调用网络接口
 */
class CategoryRepository @Inject constructor() {

    /**
     * 获取分类列表
     */
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> =
        RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))

}