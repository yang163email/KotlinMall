package com.yan.base.rx

import com.yan.base.presenter.view.BaseView
import rx.Subscriber

typealias Next<T> = (T) -> Unit
typealias Error = (Throwable) -> Unit
typealias Complete = () -> Unit
/**
 *  @author      : yan
 *  @date        : 2018/1/12 16:26
 *  @description : rx订阅帮助类，DSL编码方式
 */
class SubscriberHelper<T>(private val baseView: BaseView) : Subscriber<T>() {

    private var onNextListener: Next<T>? = null
    private var onErrorListener: Error? = null
    private var onCompleteListener: Complete? = null

    fun onNext(next: Next<T>) {
        onNextListener = next
    }

    fun onError(error: Error) {
        onErrorListener = error
    }

    fun onComplete(complete: Complete) {
        onCompleteListener = complete
    }

    override fun onNext(t: T) {
        onNextListener?.invoke(t)
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        onErrorListener?.invoke(e)
    }

    override fun onCompleted() {
        baseView.hideLoading()
        onCompleteListener?.invoke()
    }

}