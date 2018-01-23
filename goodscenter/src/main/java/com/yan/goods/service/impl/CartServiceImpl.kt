package com.yan.goods.service.impl

import com.yan.base.ext.convert
import com.yan.goods.data.repository.CartRepository
import com.yan.goods.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:40
 *  @description : 购物车 业务层 实现类
 */
class CartServiceImpl @Inject constructor(): CartService {

    @Inject
    lateinit var repository: CartRepository

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                         goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
                .convert()
    }
}
