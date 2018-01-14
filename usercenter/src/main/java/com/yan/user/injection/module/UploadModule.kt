package com.yan.user.injection.module

import com.yan.user.service.UploadService
import com.yan.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/12 20:28
 *  @description : 提供上传相关字段的module
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(service: UploadServiceImpl): UploadService = service
}