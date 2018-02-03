package com.yan.goods.service

import com.yan.goods.data.protocol.Category
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/15 11:52
 *  @description : 商品分类接口区域
 */
interface CategoryService {

    /**
     * 获取分类列表
     */
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}