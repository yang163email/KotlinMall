package com.yan.pay.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
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
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/31 19:03
 *  @description : 收银台页面
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId = 0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        initView()
    }

    private fun initView() {
        mTvTotalPrice.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
        mBtnPay.onClick { mPresenter.getPaySign(mOrderId, mTotalPrice) }
    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
                .activityComponent(activityComponent)
                .payModule(PayModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onGetSignResult(result: String) {
        toast(result)
    }
}