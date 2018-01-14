package com.yan.user.ui.activity

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
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.qiniu.android.storage.UploadManager
import com.yan.base.common.BaseConstant
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseMvpActivity
import com.yan.base.utils.DateUtils
import com.yan.base.utils.GlideUtils
import com.yan.user.R
import com.yan.user.data.protocol.UserInfo
import com.yan.user.injection.component.DaggerUserComponent
import com.yan.user.injection.module.UserModule
import com.yan.user.presenter.UserInfoPresenter
import com.yan.user.presenter.view.UserInfoView
import com.yan.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 用户信息界面
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
        TakePhoto.TakeResultListener, InvokeListener {

    private var alertView: AlertView? = null
    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    /** 选择本地文件的url */
    private var mLocalFileUrl: String? = null
    /** 远程图片url */
    private var mRemoteFileUrl: String? = null
    private var invokeParam: InvokeParam? = null

    private var mUserInfo: UserInfo? = null

    private val mUploadManager by lazy { UploadManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        getTakePhoto()
        initView()
        initData()
        mTakePhoto.onCreate(savedInstanceState)
    }

    private fun initView() {
        mHeaderBar.getRightView()
                .onClick {
                    mPresenter.editUser(
                            mRemoteFileUrl!!,
                            mEtUserName.text.toString(),
                            if (mRbGenderMale.isChecked) "0" else "1",
                            mEtUserSign.text.toString()
                            )
                }
        mRlUserIcon.onClick {
            showAlertView()
        }
    }

    private fun initData() {
        mUserInfo = UserPrefsUtils.getUserInfo()

        mUserInfo?.apply {
            mRemoteFileUrl = userIcon

            GlideUtils.loadUrlImage(this@UserInfoActivity, userIcon, mIvUserIcon)
            mEtUserName.setText(userName)
            if (userGender == "0") mRbGenderMale.isChecked = true
            else mRbGenderFemale.isChecked = true
            mTvUserMobile.text = userMobile
            mEtUserSign.setText(userSign)
        }
    }

    private fun showAlertView() {
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

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.jpg"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        mTempFile = File(cacheDir, tempFileName)
    }

    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d(TAG, "takeFail: $msg")
    }

    override fun invoke(invokeParam: InvokeParam): TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

    /**
     * 获取上传token回调
     */
    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, { key, info, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response.get("hash")

            Log.d(TAG, "onGetUploadTokenResult: $mRemoteFileUrl")
            GlideUtils.loadUrlImage(this, mRemoteFileUrl!!, mIvUserIcon)
        }, null)
    }

    /**
     * 修改成功回调
     */
    override fun onEditUserResult(userInfo: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(userInfo)
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

    /**
     * 获取TakePhoto实例
     */
    private fun getTakePhoto() {
        mTakePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
    }

    override fun onBackPressed() {
        alertView?.takeIf { it.isShowing }?.dismiss() ?: super.onBackPressed()
    }
}
