package com.yan.user.injection.module

import com.yan.user.service.UserService
import com.yan.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/12 20:28
 *  @description : 提供用户相关字段的module
 */
@Module
class UserModule {

    @Provides
    fun provideUserService(service: UserServiceImpl): UserService = service
}