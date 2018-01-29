package com.yan.order.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.fragment.BaseFragment
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.common.OrderStatus
import kotlinx.android.synthetic.main.fragment_order.*

/**
 *  @author      : yan
 *  @date        : 2018/1/29 19:00
 *  @description : 订单Fragment
 */
class OrderFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        text.text = arguments.getInt(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL).toString()
    }

}