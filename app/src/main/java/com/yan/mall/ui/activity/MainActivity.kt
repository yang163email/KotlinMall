package com.yan.mall.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.mall.R
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 *  @author      : yan
 *  @date        : 2018/1/14 13:42
 *  @description : 主界面
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mBottomNavBar.checkMsgBadge(true) }

        Observable.timer(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mBottomNavBar.checkCartBadge(0) }
    }
}
