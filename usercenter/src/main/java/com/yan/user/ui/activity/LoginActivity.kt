package com.yan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.yan.base.ext.enable2
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
import com.yan.user.data.protocol.UserInfo
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.LoginPresenter
import com.yan.user.presenter.view.LoginView
import com.yan.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 登录界面
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        mHeaderBar.getRightView().onClick(this)
        mBtnLogin.apply {
            onClick(this@LoginActivity)
            enable2(arrayOf(mEtMobile, mEtPwd)) { isBtnEnable() }
        }
        mTvForgetPwd.onClick(this)
    }

    /**
     * 判断当前按钮是否可用；只有所有编辑框都有文字才可用
     */
    private fun isBtnEnable(): Boolean =
            !mEtMobile.text.isNullOrEmpty() && !mEtPwd.text.isNullOrEmpty()

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 登录回调
     */
    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        //存储用户信息到sp中
        UserPrefsUtils.putUserInfo(result)
        startActivity<UserInfoActivity>()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mTvRight -> startActivity<RegisterActivity>()
            R.id.mBtnLogin -> {
                mPresenter.login(mEtMobile.text.toString(), mEtPwd.text.toString(), "")
            }
            R.id.mTvForgetPwd -> startActivity<ForgetPwdActivity>()
        }
    }

}
