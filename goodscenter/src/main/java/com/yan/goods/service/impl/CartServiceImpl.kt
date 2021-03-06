package com.yan.goods.service.impl

import com.yan.base.ext.convert
import com.yan.base.ext.convertBoolean
import com.yan.goods.data.protocol.CartGoods
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

    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    override fun deleteCartList(cartIdList: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(cartIdList).convertBoolean()
    }

    override fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(goodsList, totalPrice).convert()
    }

}
