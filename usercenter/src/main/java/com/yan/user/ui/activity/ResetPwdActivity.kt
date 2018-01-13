package com.yan.user.ui.activity

import android.os.Bundle
import com.yan.base.ext.enable2
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.ResetPwdPresenter
import com.yan.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 重置密码界面
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    private fun initView() {
        mBtnConfirm.apply {
            onClick {
                if (mEtPwd.text.toString() != mEtPwdConfirm.text.toString()) {
                    toast("两次密码不一致")
                    return@onClick
                }
                mPresenter.resetPwd(intent.getStringExtra("mobile"), mEtPwd.text.toString())
            }
            enable2(arrayOf(mEtPwd, mEtPwdConfirm)) { isBtnEnable() }
        }
    }

    /**
     * 判断当前按钮是否可用；只有所有编辑框都有文字才可用
     */
    private fun isBtnEnable(): Boolean =
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
     * 忘记密码回调
     */
    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

}
