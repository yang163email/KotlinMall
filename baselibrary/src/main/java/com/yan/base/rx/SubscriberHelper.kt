package com.yan.base.rx

import com.yan.base.alias.None_Unit
import com.yan.base.alias.Type_Unit
import com.yan.base.presenter.view.BaseView
import rx.Subscriber

/**
 *  @author      : yan
 *  @date        : 2018/1/12 16:26
 *  @description : rx订阅帮助类，DSL编码方式
 */
class SubscriberHelper<T>(private val baseView: BaseView) : Subscriber<T>() {

    private var onNextListener: Type_Unit<T>? = null
    private var onErrorListener: Type_Unit<Throwable>? = null
    private var onCompleteListener: None_Unit? = null

    fun onNext(next: Type_Unit<T>) {
        onNextListener = next
    }

    fun onError(error: Type_Unit<Throwable>) {
        onErrorListener = error
    }

    fun onComplete(complete: None_Unit) {
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