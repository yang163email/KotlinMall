package com.yan.base.presenter

import com.trello.rxlifecycle.LifecycleProvider
import com.yan.base.presenter.view.BaseView
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
}