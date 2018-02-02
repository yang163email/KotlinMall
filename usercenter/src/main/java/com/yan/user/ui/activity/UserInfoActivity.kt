package com.yan.user.ui.activity

import android.os.Bundle
import android.util.Log
import com.jph.takephoto.model.TResult
import com.qiniu.android.storage.UploadManager
import com.yan.base.common.BaseConstant
import com.yan.base.ext.onClick
import com.yan.base.ui.activity.BaseTakePhotoActivity
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

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 用户信息界面
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {

    /** 选择本地文件的url */
    private var mLocalFileUrl: String? = null
    /** 远程图片url */
    private var mRemoteFileUrl: String? = null

    private var mUserInfo: UserInfo? = null

    private val mUploadManager by lazy { UploadManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
        initData()
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

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
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

}
