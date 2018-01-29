package com.yan.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.data.protocol.ShipAddress
import com.yan.order.injection.component.DaggerShipAddressComponent
import com.yan.order.injection.module.ShipAddressModule
import com.yan.order.presenter.ShipAddressPresenter
import com.yan.order.presenter.view.ShipAddressView
import com.yan.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/27 17:31
 *  @description : 收货地址界面
 */
class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var mAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
                .activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mAdapter = ShipAddressAdapter(this)
        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.editShipAddress(address)
                toast("设置默认")
            }

            override fun onEdit(address: ShipAddress) {
                toast("编辑")
            }

            override fun onDelete(address: ShipAddress) {
                toast("删除")
            }
        }
        mRvAddress.apply {
            layoutManager = LinearLayoutManager(this@ShipAddressActivity)
            adapter = mAdapter
        }
        mBtnAddAddress.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }

    private fun loadData() {
        mPresenter.getShipAddressList()
    }

    override fun onGetShipAddressListResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("设置默认成功")
        loadData()
    }
}