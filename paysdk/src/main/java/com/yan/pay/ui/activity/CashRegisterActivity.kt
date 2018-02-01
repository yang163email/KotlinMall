package com.yan.pay.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.pay.R
import com.yan.pay.injection.component.DaggerPayComponent
import com.yan.pay.injection.module.PayModule
import com.yan.pay.presenter.PayPresenter
import com.yan.pay.presenter.view.PayView
import com.yan.provider.common.ProviderConstant
import com.yan.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:03
 *  @description : 收银台页面
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {

    private var mOrderId = 0
    private var mTotalPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        initData()
        initView()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(activityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun initData() {
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(ProviderConstant.KEY_ORDER_PRICE, -1L)
    }

    private fun initView() {
        mTvTotalPrice.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
        mTvAlipayType.isSelected = true
        mTvAlipayType.onClick(this)
        mTvWeixinType.onClick(this)
        mTvBankCardType.onClick(this)
        mBtnPay.onClick(this)
    }

    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if ("9000" == resultMap["resultStatus"]) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
    }

    override fun onClick(v: View?) {
        when (v) {
            mTvAlipayType -> updatePayType(true, false, false)
            mTvWeixinType -> updatePayType(false, true, false)
            mTvBankCardType -> updatePayType(false, false, true)
            mBtnPay -> mPresenter.getPaySign(mOrderId, mTotalPrice)
        }
    }

    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCardPay: Boolean) {
        mTvAlipayType.isSelected = isAliPay
        mTvWeixinType.isSelected = isWeixinPay
        mTvBankCardType.isSelected = isBankCardPay
    }
}