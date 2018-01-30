package com.yan.order.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:27
 *  @description : 添加收货地址
 */
data class AddShipAddressReq(val shipUserName: String, val shipUserMobile: String, val shipAddress: String)
