package com.yan.base.ext

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.yan.base.data.protocol.BaseResp
import com.yan.base.presenter.view.BaseView
import com.yan.base.rx.BaseFunction
import com.yan.base.rx.BaseFunctionBoolean
import com.yan.base.rx.BaseObserver
import com.yan.base.rx.SubscriberHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  @author      : yan
 *  @date        : 2018/1/13 11:12
 *  @description : 放置所有关于Rx的扩展方法
 */

/**
 * 扩展rx通用的执行方法
 */
fun <T> Observable<T>.execute(subscriber: BaseObserver<T>) {
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

/**
 * 第三种扩展方式
 */
fun <T> Observable<T>.execute2(lifecycleProvider: LifecycleProvider<*>,
                               baseView: BaseView,
                               init: SubscriberHelper<T>.() -> Unit) {
    val subscriberHelper = SubscriberHelper<T>(baseView)
    init(subscriberHelper)
    subscribeOn(Schedulers.io())
            .bindToLifecycle(lifecycleProvider)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriberHelper)
}

/**
 * 将网络返回的数据转换成对应类型Observable
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> = flatMap(BaseFunction())

/**
 * 将网络返回的数据转换成Observable<Boolean>
 */
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> = flatMap(BaseFunctionBoolean())