package com.yan.base.data.net

import com.yan.base.common.BaseConstant
import com.yan.base.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  @author      : yan
 *  @date        : 2018/1/12 17:24
 *  @description : Retrofit工厂单例类
 */
class RetrofitFactory private constructor(){

    companion object {
        val instance by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = initInterceptor()

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /**
     * OkHttpClient
     */
    private fun initClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(BaseConstant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(BaseConstant.READ_TIMEOUT, TimeUnit.SECONDS)
                .build()

    /**
     * 其他统一拦截器
     */
    private fun initInterceptor() = Interceptor { chain ->
        val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader(BaseConstant.KEY_SP_TOKEN, AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                .build()
        chain.proceed(request)
    }

    /**
     * 日志拦截器
     */
    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 暴露方法，返回Service对象
     */
    fun <T> create(service: Class<T>) = retrofit.create(service)
}