package com.yan.base.alias

/**
 *  @author      : yan
 *  @date        : 2018/1/13 14:54
 *  @description : 存放typealias的文件
 */

typealias TypeUnit<T> = (T) -> Unit
typealias NoneUnit = () -> Unit

/** textWatcher */
typealias BeforeTextChanged = (s: CharSequence, start: Int, count: Int, after: Int) -> Unit
