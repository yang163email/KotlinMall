package com.yan.base.rx

import com.yan.base.alias.T0_Unit
import com.yan.base.alias.T1_Unit
import com.yan.base.alias.Throwable_Unit
import com.yan.base.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 16:26
 *  @description : rx订阅帮助类，DSL编码方式
 */
class SubscriberHelper<T>(private val baseView: BaseView) : Observer<T> {

    private var onNextListener: T1_Unit<T>? = null
    private var onErrorListener: Throwable_Unit? = null
    private var onCompleteListener: T0_Unit? = null
    private var onSubscribeListener: T1_Unit<Disposable>? = null

    fun onNext(next: T1_Unit<T>) {
        onNextListener = next
    }

    fun onError(error: Throwable_Unit) {
        onErrorListener = error
    }

    fun onComplete(complete: T0_Unit) {
        onCompleteListener = complete
    }

    fun onSubscribe(subscribe: T1_Unit<Disposable>) {
        onSubscribeListener = subscribe
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

    override fun onComplete() {
        baseView.hideLoading()
        onCompleteListener?.invoke()
    }

    override fun onSubscribe(d: Disposable) {
        onSubscribeListener?.invoke(d)
    }

}