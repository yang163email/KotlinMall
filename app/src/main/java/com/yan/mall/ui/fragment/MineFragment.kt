package com.yan.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ui.fragment.BaseFragment
import com.yan.mall.R

/**
 *  @author      : yan
 *  @date        : 2018/1/14 18:00
 *  @description : 我的界面Fragment
 */
class MineFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_mine, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
