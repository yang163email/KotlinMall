package com.yan.user.data.api

import com.yan.base.data.protocol.BaseResp
import retrofit2.http.POST
import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 19:12
 *  @description : 上传相关的api请求接口
 */
interface UploadApi {

    /**
     * 获取七牛云上传token
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}