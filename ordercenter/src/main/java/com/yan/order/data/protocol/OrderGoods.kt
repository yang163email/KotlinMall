package com.yan.order.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:29
 *  @description : 订单中的商品
 */
data class OrderGoods(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
)

