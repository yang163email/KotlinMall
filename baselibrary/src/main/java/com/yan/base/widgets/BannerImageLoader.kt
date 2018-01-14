package com.yan.base.widgets

import android.content.Context
import android.widget.ImageView
import com.yan.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 *  @author      : yan
 *  @date        : 2018/1/14 14:50
 *  @description : banner的图片加载器
 */
class BannerImageLoader : ImageLoader() {

    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}