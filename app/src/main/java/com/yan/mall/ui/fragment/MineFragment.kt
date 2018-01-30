package com.yan.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yan.base.ext.loadUrl
import com.yan.base.ext.onClick
import com.yan.base.ui.fragment.BaseFragment
import com.yan.base.utils.AppPrefsUtils
import com.yan.mall.R
import com.yan.mall.ui.activity.SettingActivity
import com.yan.order.common.OrderConstant
import com.yan.order.common.OrderStatus
import com.yan.order.ui.activity.OrderActivity
import com.yan.order.ui.activity.ShipAddressActivity
import com.yan.provider.common.ProviderConstant
import com.yan.provider.common.afterLogin
import com.yan.provider.common.isLogin
import com.yan.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.support.v4.startActivity

/**
 *  @author      : yan
 *  @date        : 2018/1/14 18:00
 *  @description : 我的界面Fragment
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_mine, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        mIvUserIcon.onClick(this)
        mTvUserName.onClick(this)
        mTvSetting.onClick(this)
        mTvAddress.onClick(this)
        mTvWaitPayOrder.onClick(this)
        mTvWaitConfirmOrder.onClick(this)
        mTvCompleteOrder.onClick(this)
        mTvAllOrder.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogin()) {
            //头像
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) mIvUserIcon.loadUrl(userIcon)
            else mIvUserIcon.setImageResource(R.drawable.icon_default_user)
            //用户名
            mTvUserName.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mIvUserIcon.setImageResource(R.drawable.icon_default_user)
            mTvUserName.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            mIvUserIcon, mTvUserName -> {
                afterLogin { startActivity<UserInfoActivity>() }
            }
            mTvWaitPayOrder ->
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            mTvWaitConfirmOrder ->
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            mTvCompleteOrder ->
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            mTvAllOrder -> startActivity<OrderActivity>()
            mTvSetting -> startActivity<SettingActivity>()
            mTvAddress -> startActivity<ShipAddressActivity>()
        }
    }

}
