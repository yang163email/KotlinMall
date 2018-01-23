package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/23 9:32
 *  @description : 删除购物车商品请求
 */
data class DeleteCartReq(val cartIdList: List<Int> = arrayListOf())
