package com.yan.base.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.yan.base.presenter.view.BaseView
import com.yan.base.utils.NetWorkUtils
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:04
 *  @description : 所有Presenter层的基类，持有View层引用
 */
open class BasePresenter<V : BaseView> {

    lateinit var mView: V

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    protected fun checkNetWork(): Boolean{
        if (NetWorkUtils.isNetWorkAvailable(context)) return true
        mView.onError("网络不可用")
        return false
    }
}