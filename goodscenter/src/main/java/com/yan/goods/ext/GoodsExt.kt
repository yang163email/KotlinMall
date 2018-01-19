package com.yan.goods.ext

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/**
 *  @author      : yan
 *  @date        : 2018/1/19 10:05
 *  @description : 商品相关扩展
 */
fun NumberButton.getEditText(): EditText = find(R.id.text_count)