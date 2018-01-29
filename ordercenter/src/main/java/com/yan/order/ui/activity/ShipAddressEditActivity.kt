package com.yan.order.ui.activity

import android.os.Bundle
import com.yan.base.ext.isNullOrEmpty
import com.yan.base.ext.onClick
import com.yan.base.ext.textStr
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.data.protocol.ShipAddress
import com.yan.order.injection.component.DaggerShipAddressComponent
import com.yan.order.injection.module.ShipAddressModule
import com.yan.order.presenter.ShipAddressEditPresenter
import com.yan.order.presenter.view.ShipAddressEditView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:38
 *  @description : 编辑收货地址界面
 */
class ShipAddressEditActivity : BaseMvpActivity<ShipAddressEditPresenter>(), ShipAddressEditView {

    private var mAddress: ShipAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mBtnSave.onClick {
            save()
        }
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mEtShipName.setText(it.shipUserName)
            mEtShipMobile.setText(it.shipUserMobile)
            mEtShipAddress.setText(it.shipAddress)
        }
    }

    private fun save() {
        if (mEtShipName.isNullOrEmpty()) {
            toast("收货人不能为空")
            return
        }
        if (mEtShipMobile.isNullOrEmpty()) {
            toast("联系方式不能为空")
            return
        }
        if (mEtShipAddress.isNullOrEmpty()) {
            toast("详细地址不能为空")
            return
        }
        mAddress?.apply {
            shipUserName = mEtShipName.textStr
            shipUserMobile = mEtShipMobile.textStr
            shipAddress = mEtShipAddress.textStr
            mPresenter.editShipAddress(this)
        } ?: mPresenter.addShipAddress(mEtShipName.textStr, mEtShipMobile.textStr, mEtShipAddress.textStr)
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加收货地址成功")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("修改收货地址成功")
        finish()
    }
}