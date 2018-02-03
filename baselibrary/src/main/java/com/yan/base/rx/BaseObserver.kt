package com.yan.base.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:55
 *  @description : 将rx中的Observer部分拆分下来，部分统一处理
 */
open class BaseObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }

}