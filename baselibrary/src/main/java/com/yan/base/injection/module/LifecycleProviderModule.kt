package com.yan.base.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:12
 *  @description : 提供RxLifecycleProvider的module
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun provideLifecycleProvider(): LifecycleProvider<*> = lifecycleProvider
}