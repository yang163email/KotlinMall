package com.yan.order.event

import com.yan.order.data.protocol.ShipAddress

/**
 *  @author      : yan
 *  @date        : 2018/1/29 16:49
 *  @description : 选择地址事件
 */
class SelectAddressEvent(val address: ShipAddress)