package com.yan.user.injection.module

import com.yan.user.service.UserService
import com.yan.user.service.impl.UserServiceImpl
import com.yan.user.service.impl.UserServiceImpl2
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 *  @author      : yan
 *  @date        : 2018/1/12 20:28
 *  @description : 用户需要注入的模块
 */
@Module
class UserModule {

    @Named("service")
    @Provides
    fun provideUserService(service: UserServiceImpl): UserService = service

    @Named("service2")
    @Provides
    fun provideUserService2(service: UserServiceImpl2): UserService = service
}