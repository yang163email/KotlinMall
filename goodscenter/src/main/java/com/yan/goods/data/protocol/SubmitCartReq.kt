package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:32
 *  @description : 提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>, val totalPrice: Long)
