package com.yan.goods.service.impl

import com.yan.base.ext.convert
import com.yan.goods.data.protocol.Goods
import com.yan.goods.data.repository.GoodsRepository
import com.yan.goods.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:59
 *  @description : 商品 业务层 实现类
 */
class GoodsServiceImpl @Inject constructor(): GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    /**
     * 获取商品列表
     */
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> =
            repository.getGoodsList(categoryId,pageNo).convert()

    /**
     * 根据关键字查询商品
     */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> =
            repository.getGoodsListByKeyword(keyword,pageNo).convert()

    /**
     * 获取商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> =
            repository.getGoodsDetail(goodsId).convert()
}
