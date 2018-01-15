package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/15 15:50
 *  @description : 商品SKU数据类
 */
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)


