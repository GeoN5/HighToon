package com.hightoon.controller.activity

import android.content.Context
import android.media.session.MediaSession
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hightoon.R
import com.hightoon.Util.ORMUtil
import com.hightoon.Util.RetrofitUtil
import com.hightoon.controller.data.Signin
import com.hightoon.controller.data.User
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var token : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.onClick {
            login()
        }

        loginSignBtn.onClick {
            startActivity<SignUpActivity>()
        }

    }

    fun login(){
        var res = RetrofitUtil.postService.SignIn(loginId.text.toString(),loginPw.text.toString())
        res.enqueue(object : Callback<Signin>{
            override fun onFailure(call: Call<Signin>?, t: Throwable?) {

                toast("Server Error")
                Log.e("loginError",t?.message)
            }

            override fun onResponse(call: Call<Signin>?, response: Response<Signin>) {
                when {
                    response.code() == 200 -> response.body()?.let {
                        token = response.body()!!.token!!
                        ORMUtil(this@LoginActivity).tokenORM.save(response.body()!!)
                        toast("로그인을 성공하였습니다")
                        userData(token)
                        startActivity<MainActivity>()
                        finish()
                    }
                    response.code() == 404 -> {
                        toast("아이디나 비밀번호가 일치하지 않습니다.")
                        loginId.error
                        loginPw.error
                    }
                    else -> toast(response.message())
                }
            }

        })
    }

    fun userData(token : String){
        var res = RetrofitUtil.postService.Token(token) as Call<User>
        res.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<User>?, response: Response<User>) {
                if(response.code() == 200){
                    response.body()?.let {
                        ORMUtil(this@LoginActivity).userORM.save(response.body()!!)
                    }
                }else{
                    toast("유저 정보를 불러오는데 문제가 생겼습니다.")
                }
            }

        })
    }

}