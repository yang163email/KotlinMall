package com.yan.base.rx

import com.yan.base.common.ResultCode
import com.yan.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 *  @author      : yan
 *  @date        : 2018/1/13 11:04
 *  @description : Fun1转换类，接收什么参数类型就返回什么类型的Observable
 */
class BaseFun1<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}