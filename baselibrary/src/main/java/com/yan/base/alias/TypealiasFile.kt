package com.yan.base.alias

/**
 *  @author      : yan
 *  @date        : 2018/1/13 14:54
 *  @description : 存放typealias的文件
 */

/**
 * 无返回值函数类型别名
 */
typealias None_Unit = () -> Unit
typealias Type_Unit<T> = (T) -> Unit
typealias T1T2_Unit<T1, T2> = (T1, T2) -> Unit

/**
 * 有返回值函数类型别名
 */
typealias None_Return<R> = () -> R

/** textWatcher */
typealias BeforeTextChanged = (s: CharSequence, start: Int, count: Int, after: Int) -> Unit

typealias ExpandNone_Unit<T> = T.() -> Unit