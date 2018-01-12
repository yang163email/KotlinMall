package com.yan.base.rx

import rx.Subscriber

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:55
 *  @description : 将rx中的subscribe部分拆分下来，部分统一处理
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onNext(t: T) {

    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable) {
    }

}