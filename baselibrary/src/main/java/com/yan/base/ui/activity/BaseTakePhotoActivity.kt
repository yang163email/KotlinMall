package com.yan.base.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.yan.base.presenter.BasePresenter
import com.yan.base.utils.DateUtils
import java.io.File

/**
 *  @author      : yan
 *  @date        : 2018/2/2 16:16
 *  @description : 拍照、相册处理的基类Activity
 */
abstract class BaseTakePhotoActivity<P : BasePresenter<*>> : BaseMvpActivity<P>(),
        TakePhoto.TakeResultListener, InvokeListener {

    private var alertView: AlertView? = null

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private var invokeParam: InvokeParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getTakePhoto()
        mTakePhoto.onCreate(savedInstanceState)
    }

    /**
     * 获取TakePhoto实例
     */
    private fun getTakePhoto() {
        mTakePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
    }

    protected fun showAlertView() {
        alertView = AlertView.Builder()
                .setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择图片")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("拍照", "相册")
                .setOthers(null)
                .setOnItemClickListener { o, position ->
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                    when (position) {
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }
                .build()
                .setCancelable(true)
        alertView?.show()
    }

    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.jpg"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        mTempFile = File(cacheDir, tempFileName)
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d(TAG, "takeFail: $msg")
    }

    override fun invoke(invokeParam: InvokeParam): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    override fun onBackPressed() {
        alertView?.takeIf { it.isShowing }?.dismiss() ?: super.onBackPressed()
    }
}