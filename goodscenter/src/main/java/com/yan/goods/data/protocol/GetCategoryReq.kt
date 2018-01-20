package com.yan.goods.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/15 10:37
 *  @description : 获取分类列表请求bean，parent为0是一级分类
 */
data class GetCategoryReq(val parentId: Int)