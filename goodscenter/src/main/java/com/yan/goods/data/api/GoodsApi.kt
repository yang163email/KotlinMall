package com.yan.goods.data.api

import com.yan.base.data.protocol.BaseResp
import com.yan.goods.data.protocol.GetGoodsDetailReq
import com.yan.goods.data.protocol.GetGoodsListByKeywordReq
import com.yan.goods.data.protocol.GetGoodsListReq
import com.yan.goods.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:48
 *  @description : 商品接口
 */
interface GoodsApi {

    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /**
     * 获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
