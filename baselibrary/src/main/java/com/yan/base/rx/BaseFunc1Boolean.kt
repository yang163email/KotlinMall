package com.yan.base.rx

import com.yan.base.common.ResultCode
import com.yan.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 *  @author      : yan
 *  @date        : 2018/1/13 10:57
 *  @description : Func1转换为Observable<Boolean>的基础类
 */
class BaseFunc1Boolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {

    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}