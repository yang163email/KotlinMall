package com.yan.goods.common

/**
 *  @author      : yan
 *  @date        : 2018/1/15 18:02
 *  @description : 商品相关常量类
 */
class GoodsConstant {

    companion object {
        //搜索历史 本地存储
        const val SP_SEARCH_HISTORY = "searchHistory"
        //搜索商品类型
        const val KEY_SEARCH_GOODS_TYPE = "searchGoodsType"
        //按分类搜索
        const  val SEARCH_GOODS_TYPE_CATEGORY = 0
        //按关键字搜索
        const  val SEARCH_GOODS_TYPE_KEYWORD = 1
        //商品分类ID
        const  val KEY_CATEGORY_ID = "categoryId"
        //商品关键字
        const val KEY_GOODS_KEYWORD = "goodsKeyword"
        //商品ID
        const  val KEY_GOODS_ID = "goodsId"
        //sku分隔标识
        const val SKU_SEPARATOR = ","
        //购物车数量
        const val SP_CART_SIZE = "cartSize"
    }

}