package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:50
 *  @description : 按关键字搜索商品
 */
data class GetGoodsListByKeywordReq(
        val keyword: String,
        val pageNo: Int
)
