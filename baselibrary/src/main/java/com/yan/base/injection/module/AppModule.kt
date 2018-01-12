package com.yan.base.injection.module

import android.content.Context
import com.yan.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:12
 *  @description : Application级别的module
 */
@Module
class AppModule(private val application: BaseApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context = application
}