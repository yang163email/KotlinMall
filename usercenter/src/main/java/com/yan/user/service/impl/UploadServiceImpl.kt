package com.yan.user.service.impl

import com.yan.base.ext.convert
import com.yan.user.data.repository.UploadRepository
import com.yan.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:46
 *  @description : 上传Service实现类，调用数据仓库进行网络请求
 */
class UploadServiceImpl @Inject constructor() : UploadService {

    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> =
            repository.getUploadToken().convert()

}