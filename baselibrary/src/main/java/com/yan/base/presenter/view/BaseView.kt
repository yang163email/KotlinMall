package com.yan.base.presenter.view

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:04
 *  @description : 所有View层的上层接口
 */
interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun onError(msg: String)
}