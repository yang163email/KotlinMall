package com.yan.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.yan.base.ext.onClick
import com.yan.base.ext.startLoading
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.order.R
import com.yan.order.common.OrderConstant
import com.yan.order.data.protocol.ShipAddress
import com.yan.order.event.SelectAddressEvent
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
    private var alertView: AlertView? = null

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
        initAdapter()
        mRvAddress.apply {
            layoutManager = LinearLayoutManager(this@ShipAddressActivity)
            adapter = mAdapter
        }
        mBtnAddAddress.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }

    private fun initAdapter() {
        mAdapter = ShipAddressAdapter(this)
        mAdapter.setOnItemClickListener { shipAddress, _ ->
            Bus.send(SelectAddressEvent(shipAddress))
            finish()
        }
        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.editShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                clickDelete(address.id)
            }
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    private fun clickDelete(id: Int) {
        alertView = AlertView.Builder()
                .setContext(this)
                .setStyle(AlertView.Style.Alert)
                .setTitle("删除")
                .setMessage("确认删除改地址？")
                .setCancelText("取消")
                .setOthers(arrayOf("确定"))
                .setOnItemClickListener { o, position ->
                    if (position == 0)
                        mPresenter.deleteShipAddress(id)
                }
                .build()
                .setCancelable(true)
        alertView?.show()
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

    override fun onDeleteShipAddressResult(result: Boolean) {
        toast("删除成功")
        loadData()
    }

    override fun onBackPressed() {
        alertView?.takeIf { it.isShowing }?.dismiss() ?: super.onBackPressed()
    }
}