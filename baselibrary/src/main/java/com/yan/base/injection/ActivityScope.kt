package com.yan.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 *  @author      : yan
 *  @date        : 2018/1/12 22:19
 *  @description : Activity作用域
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope