package com.yan.base.ui.activity

import com.yan.base.presenter.BasePresenter
import com.yan.base.presenter.view.BaseView

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:07
 *  @description : 标准MVP架构的基类Activity，属于View层，并持有P层引用
 */
open class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: P

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }
}