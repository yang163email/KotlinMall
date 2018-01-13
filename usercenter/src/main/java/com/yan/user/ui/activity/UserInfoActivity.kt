package com.yan.user.ui.activity

import android.os.Bundle
import com.bigkoo.alertview.AlertView
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.user.R
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.UserInfoPresenter
import com.yan.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 用户信息界面
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {

    private var alertView: AlertView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
    }

    private fun initView() {
        mRlUserIcon.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
        alertView = AlertView.Builder()
                .setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择图片")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("拍照", "相册")
                .setOthers(null)
                .setOnItemClickListener { o, position ->
                    when (position) {
                        0 -> toast("拍照")
                        1 -> toast("相册")
                    }
                }
                .build()
                .setCancelable(true)
        alertView?.show()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onBackPressed() {
        alertView?.takeIf { it.isShowing }?.dismiss() ?: super.onBackPressed()
    }
}
