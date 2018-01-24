package com.yan.goods.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.goods.data.api.CartApi
import com.yan.goods.data.protocol.AddCartReq
import com.yan.goods.data.protocol.CartGoods
import com.yan.goods.data.protocol.DeleteCartReq
import com.yan.goods.data.protocol.SubmitCartReq
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:33
 *  @description : 购物车数据层
 */
class CartRepository @Inject constructor() {

    /**
     * 添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResp<Int>> =
            RetrofitFactory.instance.create(CartApi::class.java)
                    .addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))

    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>> =
            RetrofitFactory.instance.create(CartApi::class.java).getCartList()

    fun deleteCartList(cartIdList: List<Int>): Observable<BaseResp<String>> =
            RetrofitFactory.instance.create(CartApi::class.java).deleteCartList(DeleteCartReq(cartIdList))

    fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<BaseResp<Int>> =
            RetrofitFactory.instance.create(CartApi::class.java).submitCart(SubmitCartReq(goodsList, totalPrice))
}
