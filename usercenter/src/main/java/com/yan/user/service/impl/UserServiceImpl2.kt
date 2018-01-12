package com.yan.user.service.impl

import com.yan.base.data.protocol.BaseResp
import com.yan.base.rx.BaseException
import com.yan.user.data.repository.UserRepository
import com.yan.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:46
 *  @description : 用户Service实现类，调用数据仓库进行网络请求
 */
class UserServiceImpl2 @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode)
                .flatMap(Func1<BaseResp<String>, Observable<Boolean>> { t ->
                    if (t.status != 0) {
                        return@Func1 Observable.error(BaseException(t.status, t.message))
                    }
                    return@Func1 Observable.just(false)
                })

    }
}