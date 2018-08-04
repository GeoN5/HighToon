package com.hightoon.controller.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hightoon.R
import com.hightoon.Util.RetrofitUtil
import com.hightoon.controller.data.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import kotlin.math.sign
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    var id = ""
    var passwd = ""
    var name = ""
    var phone = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signupBtn.onClick {
            id = signupId.text.toString()
            passwd = signupPw.text.toString()
            name = signupName.text.toString()
            phone = signupName.text.toString()

            if (id.isEmpty()) {
                signupId.error = "아이디를 입력해 주세요"
            }

            if (passwd.isEmpty()) {
                signupId.error = "비밀번호를 입력해 주세요"
            }

            if (name.isEmpty()) {
                signupId.error = "이름을 입력해 주세요"
            }

            if (phone.isEmpty()) {
                signupId.error = "휴대폰번호를 입력해 주세요"
            }

            if (id.isNotEmpty() && passwd.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty()) {
                signup()
            }

        }
    }

    fun signup() {
        var res = RetrofitUtil.postService.SignUp(
            id,
            passwd,
            name,
            phone
        )

        res.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                toast("Server Error")
                Log.e("SignupError",t?.message)
            }

            override fun onResponse(call: Call<User>?, response: Response<User>) {
                Log.e(response.code().toString(),response.message())
                when {
                    response.code() == 200 -> response.body()?.let {
                        toast("회원가입이 정상적으로 완료되었습니다.")
                        startActivity<LoginActivity>()
                        finish()
                    }
                    response.code() == 401 -> signupId.error = "이미 아이디가 존재합니다."
                    else -> toast(response.message())
                }
            }
        })
    }
}
