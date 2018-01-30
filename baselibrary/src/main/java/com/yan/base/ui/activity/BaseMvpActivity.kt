package com.yan.base.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.yan.base.common.BaseApplication
import com.yan.base.injection.component.ActivityComponent
import com.yan.base.injection.component.DaggerActivityComponent
import com.yan.base.injection.module.ActivityModule
import com.yan.base.injection.module.LifecycleProviderModule
import com.yan.base.presenter.BasePresenter
import com.yan.base.presenter.view.BaseView
import com.yan.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:07
 *  @description : 标准MVP架构的基类Activity，属于View层，并持有P层引用
 */
abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: P

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
        mLoadingDialog = ProgressLoading.create(this)
        //ARouter注册
        ARouter.getInstance().inject(this)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(msg: String) {
        toast(msg)
    }
}