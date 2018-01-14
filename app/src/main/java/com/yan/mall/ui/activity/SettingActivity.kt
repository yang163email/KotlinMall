package com.yan.mall.ui.activity

import android.os.Bundle
import android.view.View
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseActivity
import com.yan.mall.R
import com.yan.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 *  @author      : yan
 *  @date        : 2018/1/14 22:11
 *  @description : 设置界面
 */
class SettingActivity : BaseActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initView()
    }

    private fun initView() {
        mBtnLogout.onClick(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mBtnLogout -> {
                UserPrefsUtils.putUserInfo(null)
                finish()
            }
        }
    }
}