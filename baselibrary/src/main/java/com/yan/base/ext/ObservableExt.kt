package com.yan.base.ext

import com.trello.rxlifecycle.LifecycleProvider
import com.yan.base.data.protocol.BaseResp
import com.yan.base.rx.BaseFun1
import com.yan.base.rx.BaseFunc1Boolean
import com.yan.base.rx.BaseSubscriber
import com.yan.base.rx.SubscriberHelper
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  @author      : yan
 *  @date        : 2018/1/13 11:12
 *  @description : 放置所有关于Rx的扩展方法
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
                override fun onNext(t: T) { onNext(t) }

                override fun onError(e: Throwable) { onError(e) }

                override fun onCompleted() { onComplete() }
            })
}

/**
 * 第三种扩展方式
 */
fun <T> Observable<T>.execute2(lifecycleProvider: LifecycleProvider<*>,
                               init: SubscriberHelper<T>.() -> Unit) {
    val subscriberHelper = SubscriberHelper<T>()
    init(subscriberHelper)
    subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriberHelper)
}

/**
 * 将网络返回的数据转换成对应类型Observable
 */
fun <T> Observable<BaseResp<T>>.convert() = flatMap(BaseFun1())

/**
 * 将网络返回的数据转换成Observable<Boolean>
 */
fun <T> Observable<BaseResp<T>>.convertBoolean() = flatMap(BaseFunc1Boolean())