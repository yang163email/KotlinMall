package com.yan.order.data.protocol

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  @author      : yan
 *  @date        : 2018/1/27 11:29
 *  @description : 收货地址
 */
@Parcelize
data class ShipAddress(
        val id: Int,
        var shipUserName: String,
        var shipUserMobile: String,
        var shipAddress: String,
        var shipIsDefault: Int
) : Parcelable
