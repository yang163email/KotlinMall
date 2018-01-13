package com.yan.user.ui.activity

import android.os.Bundle
import android.view.View
import com.yan.base.common.AppManager
import com.yan.base.ext.enable
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
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    /** 按下back键的时间，默认0 */
    private var pressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        mBtnRegister.onClick(this)
        mBtnVerifyCode.onClick(this)

        //监听下面几个，为了知道注册按钮是否可用
        mBtnRegister.enable(mEtMobile) { isBtnEnable() }
        mBtnRegister.enable(mEtVerifyCode) { isBtnEnable() }
        mBtnRegister.enable(mEtPwd) { isBtnEnable() }
        mBtnRegister.enable(mEtPwdConfirm) { isBtnEnable() }
    }

    /**
     * 判断当前按钮是否可用；只有所有编辑框都有文字才可用
     */
    private fun isBtnEnable(): Boolean =
            !mEtMobile.text.isNullOrEmpty() && !mEtVerifyCode.text.isNullOrEmpty() &&
                    !mEtPwd.text.isNullOrEmpty() && !mEtPwdConfirm.text.isNullOrEmpty()

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    /**
     * 注册回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mBtnVerifyCode -> {
                mBtnVerifyCode.requestSendVerifyNumber()
                //发送验证码业务，此处简化不写
                toast("发送验证码成功")
            }
            R.id.mBtnRegister -> {
                mPresenter.register(mEtMobile.text.toString(),
                        mEtPwd.text.toString(), mEtVerifyCode.text.toString())
            }
        }
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出")
            pressTime = time
        } else AppManager.instance.exitApp(this)
    }
}
