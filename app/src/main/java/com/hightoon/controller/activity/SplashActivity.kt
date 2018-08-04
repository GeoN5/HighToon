package com.hightoon.controller.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.hightoon.R
import com.hightoon.Util.ORMUtil
import com.hightoon.controller.data.Signin
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity : AppCompatActivity() {
    var token : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        try {
            var list: List<Any> = ORMUtil(this@SplashActivity).tokenORM.find(Signin())
            var sign = list[list.size - 1] as Signin
            token = sign.token
            Log.e("token", token)
        } catch (e: ArrayIndexOutOfBoundsException) {
            token = null
        }

        Handler().postDelayed({
            if (token != null) {
                toast("자동로그인 완료 되었습니다.")
                startActivity<MainActivity>()
                finish()
            } else {
                toast("로그인이 필요한 서비스 입니다.")
                startActivity<LoginActivity>()
                finish()
            }
        }, 2000)
    }
}
