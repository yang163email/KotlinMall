package com.yan.user.data.repository

import com.yan.base.data.net.RetrofitFactory
import com.yan.base.data.protocol.BaseResp
import com.yan.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:17
 *  @description : 上传仓库类，Retrofit调用网络接口
 */
class UploadRepository @Inject constructor() {

    /**
     * 获取七牛云上传token
     */
    fun getUploadToken(): Observable<BaseResp<String>> =
        RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()

}