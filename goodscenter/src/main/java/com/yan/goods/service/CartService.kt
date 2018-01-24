package com.yan.goods.service

import com.yan.goods.data.protocol.CartGoods
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

    /**
     * 获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /**
     * 删除购物车商品
     */
    fun deleteCartList(cartIdList: List<Int>): Observable<Boolean>

    /**
     * 提交购物车
     */
    fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<Int>
}
