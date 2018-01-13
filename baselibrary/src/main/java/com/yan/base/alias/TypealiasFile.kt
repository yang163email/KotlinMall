package com.yan.base.alias

/**
 *  @author      : yan
 *  @date        : 2018/1/13 14:54
 *  @description : 存放typealias的文件
 */

typealias NoneUnit = () -> Unit
typealias TypeUnit<T> = (T) -> Unit
typealias T1T2Unit<T1, T2> = (T1, T2) -> Unit

/** textWatcher */
typealias BeforeTextChanged = (s: CharSequence, start: Int, count: Int, after: Int) -> Unit

typealias ExpandUnit<T> = T.() -> Unit