package com.yan.goods.service

import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:39
 *  @description : 购物车 业务层 接口
 */
interface CartService {

    /**
     * 添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

}
