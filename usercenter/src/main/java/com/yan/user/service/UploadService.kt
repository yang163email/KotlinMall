package com.yan.user.service

import io.reactivex.Observable

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:44
 *  @description : 用户接口区域
 */
interface UploadService {

    fun getUploadToken(): Observable<String>

}