package com.yan.order.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:29
 *  @description : 订单数据类
 */
data class Order(
        val id: Int,
        val payType: Int,
        var shipAddress: ShipAddress?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoods>
)

