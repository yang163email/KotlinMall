package com.yan.base.ext

import com.trello.rxlifecycle.LifecycleProvider
import com.yan.base.alias.ExpandNone_Unit
import com.yan.base.alias.None_Unit
import com.yan.base.alias.Type_Unit
import com.yan.base.data.protocol.BaseResp
import com.yan.base.presenter.view.BaseView
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
 *  第二种扩展方式
 *  已废弃，由于其已经有了三个分开的回调，可以不使用
 *  直接使用.subscribe({ //onNext操作 }, { //onError操作 }, { //onComplete操作 })
 */
fun <T> Observable<T>.execute1(onNext: Type_Unit<T>,
                               onError: Type_Unit<Throwable> = {},
                               onComplete: None_Unit = {}) {
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
                               baseView: BaseView,
                               init: ExpandNone_Unit<SubscriberHelper<T>>) {
    val subscriberHelper = SubscriberHelper<T>(baseView)
    init(subscriberHelper)
    subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriberHelper)
}

/**
 * 将网络返回的数据转换成对应类型Observable
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> = flatMap(BaseFun1())

/**
 * 将网络返回的数据转换成Observable<Boolean>
 */
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> = flatMap(BaseFunc1Boolean())