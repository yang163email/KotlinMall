package com.yan.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.yan.base.alias.ExpandNone_Unit
import com.yan.base.alias.None_Return
import com.yan.base.alias.Type_Unit
import com.yan.base.widgets.TextWatcherHelper

/**
 *  @author      : yan
 *  @date        : 2018/1/12 15:58
 *  @description : 放置通用扩展方法的文件
 */

/**
 * 让setOnClickListener写起来更短
 */
fun View.onClick(block: Type_Unit<View>) {
    setOnClickListener { block(this) }
}

fun View.onClick(listener: View.OnClickListener) {
    setOnClickListener(listener)
}

/**
 * 给EditText设置DSL风格的TextChanged监听器
 */
fun EditText.onTextChangedListener(block: ExpandNone_Unit<TextWatcherHelper>) {
    val helper = TextWatcherHelper()
    block(helper)
    addTextChangedListener(helper)
}

/**
 * 扩展Button是否可用,单个EditText监听
 */
fun Button.enable(et: EditText, enable: None_Return<Boolean>) {
    et.onTextChangedListener {
        onTextChanged { s, start, count, after ->
            this@enable.isEnabled = enable()
        }
    }
}

/**
 * 扩展Button是否可用，支持多个EditText监听
 */
fun Button.enable2(etList: Array<EditText>, enable: None_Return<Boolean>) {
    etList.forEach {
        it.onTextChangedListener {
            onTextChanged { s, start, count, after ->
                this@enable2.isEnabled = enable()
            }
        }
    }
}