package com.yan.order.data.protocol

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:30
 *  @description : 修改收货地址
 */
data class EditShipAddressReq(val id:Int,val shipUserName:String,val shipUserMobile:String,val shipAddress:String,val shipIsDefault:Int)
