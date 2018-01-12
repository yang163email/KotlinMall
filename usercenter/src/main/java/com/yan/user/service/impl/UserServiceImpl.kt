package com.yan.user.service.impl

import com.yan.user.service.UserService
import rx.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:46
 *  @description : todo
 */
class UserServiceImpl : UserService {

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}