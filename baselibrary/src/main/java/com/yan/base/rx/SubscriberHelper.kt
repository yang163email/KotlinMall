package com.yan.base.rx

import com.yan.base.alias.T0_Unit
import com.yan.base.alias.T1_Unit
import com.yan.base.alias.Throwable_Unit
import com.yan.base.presenter.view.BaseView
import rx.Subscriber

/**
 *  @author      : yan
 *  @date        : 2018/1/12 16:26
 *  @description : rx订阅帮助类，DSL编码方式
 */
class SubscriberHelper<T>(private val baseView: BaseView) : Subscriber<T>() {

    private var onNextListener: T1_Unit<T>? = null
    private var onErrorListener: Throwable_Unit? = null
    private var onCompleteListener: T0_Unit? = null

    fun onNext(next: T1_Unit<T>) {
        onNextListener = next
    }

    fun onError(error: Throwable_Unit) {
        onErrorListener = error
    }

    fun onComplete(complete: T0_Unit) {
        onCompleteListener = complete
    }

    override fun onNext(t: T) {
        onNextListener?.invoke(t)
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        }
        onErrorListener?.invoke(e)
    }

    override fun onCompleted() {
        baseView.hideLoading()
        onCompleteListener?.invoke()
    }

}