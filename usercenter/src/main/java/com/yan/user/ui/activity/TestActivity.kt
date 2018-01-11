package com.yan.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.user.R
import org.jetbrains.anko.toast

/**
 *  @author      : yan
 *  @date        : 2018/1/12 0:02
 *  @description : TestActivity
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        toast("${intent.getIntExtra("id", -1)}")
    }
}
