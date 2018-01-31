package com.yan.pay.ui.activity

import android.os.Bundle
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
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView {

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
        mBtnPay.onClick { mPresenter.getPaySign(mOrderId, mTotalPrice) }
    }

    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if ("9000" == resultMap["resultStatus"]) {
                    toast("支付成功")
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }
}