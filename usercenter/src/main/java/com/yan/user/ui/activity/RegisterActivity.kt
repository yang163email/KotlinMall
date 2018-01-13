package com.yan.user.ui.activity

import android.os.Bundle
import com.yan.base.common.AppManager
import com.yan.base.ext.onClick
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

    /** 按下back键的时间，默认0 */
    private var pressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick {
            mPresenter.register(mEtMobile.text.toString(),
                    mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
        mBtnGetVerifyCode.onClick {
            mBtnGetVerifyCode.setOnVerifyBtnClick {
                println("--------")
            }
            mBtnGetVerifyCode.requestSendVerifyNumber()
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出")
            pressTime = time
        } else AppManager.instance.exitApp(this)
    }
}
