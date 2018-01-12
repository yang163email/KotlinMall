package com.yan.user.ui.activity

import android.os.Bundle
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
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

        mPresenter = RegisterPresenter()
        mPresenter.mView = this
        mRegisterBtn.setOnClickListener {
            mPresenter.register(mEtMobile.text.toString(),
                    mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
    }

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }
}
