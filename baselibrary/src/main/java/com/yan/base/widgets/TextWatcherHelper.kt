package com.yan.base.widgets

import android.text.Editable
import android.text.TextWatcher
import com.yan.base.alias.BeforeTextChanged
import com.yan.base.alias.Editable_Unit

/**
 *  @author      : yan
 *  @date        : 2018/1/13 14:40
 *  @description : TextWatcher帮助类，DSL编码方式
 */
class TextWatcherHelper : TextWatcher {

    private var beforeTextChanged: BeforeTextChanged? = null
    private var onTextChanged: BeforeTextChanged? = null
    private var editable: Editable_Unit? = null

    fun beforeTextChanged(beforeTextChanged: BeforeTextChanged) {
        this.beforeTextChanged = beforeTextChanged
    }

    fun onTextChanged(onTextChanged: BeforeTextChanged) {
        this.onTextChanged = onTextChanged
    }

    fun afterTextChanged(editable: Editable_Unit) {
        this.editable = editable
    }

    override fun afterTextChanged(s: Editable) {
        editable?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }
}