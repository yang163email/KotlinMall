package com.yan.base.ext

import android.view.View
import com.yan.base.alias.TypeUnit

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:58
 *  @description : 放置通用扩展方法的文件
 */

/**
 * 让setOnClickListener写起来更短
 */
fun View.onClick(block: TypeUnit<View>) {
    setOnClickListener { block(this) }
}