package com.yan.base.ui.activity

import android.os.Bundle
import com.yan.base.common.BaseApplication
import com.yan.base.injection.component.ActivityComponent
import com.yan.base.injection.component.DaggerActivityComponent
import com.yan.base.injection.module.ActivityModule
import com.yan.base.injection.module.LifecycleProviderModule
import com.yan.base.presenter.BasePresenter
import com.yan.base.presenter.view.BaseView
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:07
 *  @description : 标准MVP架构的基类Activity，属于View层，并持有P层引用
 */
open class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: P

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }
}