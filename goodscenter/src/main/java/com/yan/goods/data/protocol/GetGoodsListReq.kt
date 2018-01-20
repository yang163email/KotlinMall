package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:49
 *  @description : 按分类搜索商品,请求bean
 */
data class GetGoodsListReq(val categoryId: Int,val pageNo: Int)
