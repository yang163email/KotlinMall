package com.yan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.yan.base.ext.enable2
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.ForgetPwdPresenter
import com.yan.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        mBtnVerifyCode.onClick(this)
        mBtnNext.apply {
            onClick(this@ForgetPwdActivity)
            enable2(arrayOf(mEtMobile, mEtVerifyCode)) { isBtnEnable() }
        }
    }

    /**
     * 判断当前按钮是否可用；只有所有编辑框都有文字才可用
     */
    private fun isBtnEnable(): Boolean =
            !mEtMobile.text.isNullOrEmpty() && !mEtVerifyCode.text.isNullOrEmpty()

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 忘记密码回调
     */
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mEtMobile.text.toString())
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mBtnVerifyCode -> {
                mBtnVerifyCode.requestSendVerifyNumber()
                //发送验证码业务，此处简化不写
                toast("发送验证码成功")
            }
            R.id.mBtnNext -> mPresenter.forgetPwd(mEtMobile.text.toString(), mEtVerifyCode.text.toString())
        }
    }

}
