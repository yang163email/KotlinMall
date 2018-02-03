package com.yan.goods.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.goods.data.api.GoodsApi
import com.yan.goods.data.protocol.GetGoodsDetailReq
import com.yan.goods.data.protocol.GetGoodsListByKeywordReq
import com.yan.goods.data.protocol.GetGoodsListReq
import com.yan.goods.data.protocol.Goods
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:51
 *  @description : 商品数据层
 */
class GoodsRepository @Inject constructor() {

    /**
     * 根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> =
            RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))

    /**
     * 根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> =
            RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))

    /**
     * 商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> =
            RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))

}
