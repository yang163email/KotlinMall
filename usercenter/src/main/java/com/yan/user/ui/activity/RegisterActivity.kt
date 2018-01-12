package com.yan.user.ui.activity

import android.os.Bundle
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.RegisterPresenter
import com.yan.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()
        mRegisterBtn.setOnClickListener {
            mPresenter.register(mEtMobile.text.toString(),
                    mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
        mBtnGetVerifyCode.setOnClickListener {
            mPresenter.register2(mEtMobile.text.toString(),
                    mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
    }

    private fun initInjection() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: Boolean) {
        if (result) toast("注册成功") else toast("注册失败")
    }
}
