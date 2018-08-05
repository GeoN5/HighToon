package com.hightoon.Util

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eclipsesource.json.Json
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hightoon.Util.RetrofitUtil.MULTIPART_FORM_DATA
import com.hightoon.controller.data.SeasonList
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.simple.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


fun ImageView.loadImage(url : String,context: Context){//회원가입 할 때
    Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(this)
}

fun ImageView.loadImage(url : Uri, context: Context){//수정 할 때
    Glide.with(context).load(url).apply(RequestOptions().centerCrop()).into(this)
}

object RetrofitUtil {

    var retrofit = Retrofit.Builder()
            .baseUrl("http://iwin247.kr:3030")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val MULTIPART_FORM_DATA = "multipart/form-data"

    val postService = retrofit!!.create(Services::class.java)

    fun season(season : String){
        var res = RetrofitUtil.postService.Season(season)
        res.enqueue(object : Callback<SeasonList> {
            override fun onResponse(call: Call<SeasonList>?, response: Response<SeasonList>) {
                if(response.code() == 200){
                    Log.e("아아앙",season)
                    Log.e("아아아앙", Gson().toJson(response!!.body()!!.list))
                }else{
                    Log.e("로그를 머로찍지","정보존재x")
                }

            }

            override fun onFailure(call: Call<SeasonList>?, t: Throwable?) {
                Log.e("season" + season + "Error", t!!.message)
            }
        })

    }

    fun createRequestBody(file: File, name: String): MultipartBody.Part {
        val mFile = RequestBody.create(MediaType.parse("images/*"), file)
        return MultipartBody.Part.createFormData(name, file.name, mFile)
    }

    fun createRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), value)
    }


    object DateUtil {
        fun formatDate(dateData: String): String {
            //JS 고유 날짜 포맷 형식객체를 만듬.
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
            //인자를 형변환해서 Date 타입 객체로 받음.
            var date: Date = inputFormat.parse(dateData)
            //바꿀 포맷 형식으로 객체 생성.
            var simpleDataFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //format 메소드를 통해 바꿔서 리턴.
            return simpleDataFormat.format(date)
        }
    }
}