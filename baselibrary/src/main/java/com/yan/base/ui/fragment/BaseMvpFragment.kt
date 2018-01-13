package com.yan.base.ui.fragment

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
 *  @description : 标准MVP架构的基类Fragment，属于View层，并持有P层引用
 */
abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: P

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
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