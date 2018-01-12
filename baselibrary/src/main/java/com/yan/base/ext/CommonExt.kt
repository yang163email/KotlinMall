package com.yan.base.ext

import com.yan.base.rx.BaseSubscriber
import com.yan.base.rx.SubscriberHelper
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:58
 *  @description : 放置通用扩展方法的文件
 */

/**
 * 扩展rx通用的执行方法
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>) {
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

/**
 * 第二种扩展方式
 */
fun <T> Observable<T>.execute1(onNext: (T) -> Unit,
                              onError: (Throwable) -> Unit = {},
                              onComplete: () -> Unit = {}) {
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseSubscriber<T>() {
                override fun onNext(t: T) {
                    onNext(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

                override fun onCompleted() {
                    onComplete()
                }
            })
}

/**
 * 第三种扩展方式
 */
fun <T> Observable<T>.execute2(init: SubscriberHelper<T>.() -> Unit) {
    val subscriberHelper = SubscriberHelper<T>()
    init(subscriberHelper)
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriberHelper)
}