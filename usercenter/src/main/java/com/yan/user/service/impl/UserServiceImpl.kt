package com.yan.user.service.impl

import com.yan.base.ext.convert
import com.yan.base.ext.convertBoolean
import com.yan.user.data.protocol.UserInfo
import com.yan.user.data.repository.UserRepository
import com.yan.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:46
 *  @description : 用户Service实现类，调用数据仓库进行网络请求
 */
class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> =
            repository.register(mobile, pwd, verifyCode).convertBoolean()

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> =
            repository.login(mobile, pwd, pushId).convert()

}