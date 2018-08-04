package com.hightoon.Util

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hightoon.Util.RetrofitUtil.MULTIPART_FORM_DATA
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
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
    }

    fun createRequestBody(file: File, name: String): MultipartBody.Part {
        val mFile = RequestBody.create(MediaType.parse("images/*"), file)
        return MultipartBody.Part.createFormData(name, file.name, mFile)
    }

    fun createRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), value)
    }


//object SharedPreferenceUtil {
//
//    fun getData(context : Context, key : String) : String? {
//        var sharedPreferences : SharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE)
//        return sharedPreferences.getString(key, null)
//    }
//
//    fun saveData(context: Context, key : String, value : String) {
//        var sharedPreferences : SharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
//        var editor : SharedPreferences.Editor = sharedPreferences.edit()
//        editor.putString(key, value)
//        editor.commit()
//    }
//
//}

object DateUtil{
    fun formatDate(dateData : String):String{
        //JS 고유 날짜 포맷 형식객체를 만듬.
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        //인자를 형변환해서 Date 타입 객체로 받음.
        var date : Date = inputFormat.parse(dateData)
        //바꿀 포맷 형식으로 객체 생성.
        var simpleDataFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //format 메소드를 통해 바꿔서 리턴.
        return simpleDataFormat.format(date)
    }
}