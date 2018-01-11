package com.yan.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yan.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity

/**
 *  @author      : yan
 *  @date        : 2018/1/11 22:34
 *  @description : 注册界面
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.setOnClickListener {
            startActivity<TestActivity>("id" to 10)
        }
    }
}
