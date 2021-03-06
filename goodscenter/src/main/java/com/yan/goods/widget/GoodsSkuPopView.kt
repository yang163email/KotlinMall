package com.yan.goods.widget

import android.app.Activity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.PopupWindow
import android.widget.RelativeLayout
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import com.yan.base.ext.loadUrl
import com.yan.base.ext.onClick
import com.yan.base.ext.onTextChangedListener
import com.yan.goods.R
import com.yan.goods.common.GoodsConstant
import com.yan.goods.data.protocol.GoodsSku
import com.yan.goods.event.AddCartEvent
import com.yan.goods.event.SkuChangedEvent
import com.yan.goods.ext.getEditText
import kotlinx.android.synthetic.main.layout_sku_pop.view.*
import org.jetbrains.anko.find

/**
 *  @author      : yan
 *  @date        : 2018/1/18 15:39
 *  @description : 商品SKU弹层
 */
class GoodsSkuPopView(val context: Activity) : PopupWindow(context), View.OnClickListener {
    //根视图
    private val mRootView: View
    private val mSkuViewList = arrayListOf<SkuView>()

    init {
        mRootView = LayoutInflater.from(context).inflate(R.layout.layout_sku_pop, null)

        initView()

        // 设置SelectPicPopupWindow的View
        this.contentView = mRootView
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.width = LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.animationStyle = R.style.AnimBottom
        background.alpha = 150
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mRootView.setOnTouchListener { _, event ->
            val height = mRootView.find<RelativeLayout>(R.id.mPopView).top
            val y = event.y.toInt()
            if (event.action == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss()
                }
            }
            true
        }


    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mRootView.mIvClose.onClick(this)
        mRootView.mBtnAddCart.onClick(this)

        mRootView.mBtnSkuCount.setCurrentNumber(1)
                mRootView.mBtnSkuCount.getEditText().onTextChangedListener {
                    onTextChanged { _, _, _, _ ->
                        Bus.send(SkuChangedEvent())
                    }
                }

        mRootView.mBtnAddCart.onClick {
            Bus.send(AddCartEvent())
            dismiss()
        }
    }

    /**
     * 设置商品图标
     */
    fun setGoodsIcon(text: String) {
        mRootView.mIvGoodsIcon.loadUrl(text)
    }

    /**
     * 设置商品价格
     */
    fun setGoodsPrice(text: Long) {
        mRootView.mTvGoodsPrice.text = YuanFenConverter.changeF2YWithUnit(text)
    }

    /**
     * 设置商品编号
     */
    fun setGoodsCode(text: String) {
        mRootView.mTvGoodsCode.text = "商品编号:" + text
    }

    /**
     * 设置商品SKU
     */
    fun setSkuData(list: List<GoodsSku>) {
        for (goodSku in list) {
            val skuView = SkuView(context)
            skuView.setSkuData(goodSku)

            mSkuViewList.add(skuView)
            mRootView.mSkuView.addView(skuView)
        }
    }

    /**
     * 获取选中的SKU
     */
    fun getSelectSku(): String {
        var skuInfo = ""
        for (skuView in mSkuViewList) {
            skuInfo += skuView.getSkuInfo().split(GoodsConstant.SKU_SEPARATOR)[1] + GoodsConstant.SKU_SEPARATOR
        }
        return skuInfo.take(skuInfo.length - 1) //刪除最后一个分隔
    }

    /**
     * 获取商品数量
     */
    fun getSelectCount() = mRootView.mBtnSkuCount.number

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mIvClose    -> dismiss()
            R.id.mBtnAddCart -> {
                dismiss()
            }
        }
    }

}
