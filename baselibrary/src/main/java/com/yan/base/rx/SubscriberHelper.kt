package com.yan.base.rx

import com.yan.base.alias.NoneUnit
import com.yan.base.alias.TypeUnit
import com.yan.base.presenter.view.BaseView
import rx.Subscriber

/**
 *  @author      : yan
 *  @date        : 2018/1/12 16:26
 *  @description : rx订阅帮助类，DSL编码方式
 */
class SubscriberHelper<T>(private val baseView: BaseView) : Subscriber<T>() {

    private var onNextListener: TypeUnit<T>? = null
    private var onErrorListener: TypeUnit<Throwable>? = null
    private var onCompleteListener: NoneUnit? = null

    fun onNext(next: TypeUnit<T>) {
        onNextListener = next
    }

    fun onError(error: TypeUnit<Throwable>) {
        onErrorListener = error
    }

    fun onComplete(complete: NoneUnit) {
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