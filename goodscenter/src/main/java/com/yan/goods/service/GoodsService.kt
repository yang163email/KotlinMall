package com.yan.goods.service

import com.yan.goods.data.protocol.Goods
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:53
 *  @description : 商品 业务层 接口
 */
interface GoodsService {

    /**
     * 获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /**
     * 根据关键字查询商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /**
     * 获取商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}
