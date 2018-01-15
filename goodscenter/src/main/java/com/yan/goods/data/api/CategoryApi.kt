package com.yan.goods.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.goods.data.protocol.Category
import com.yan.goods.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/15 10:35
 *  @description : 商品分类接口
 */
interface CategoryApi {

    /**
     * 获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}