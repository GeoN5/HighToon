package com.hightoon.Util

import android.media.session.MediaSession
import com.hightoon.controller.data.SeasonList
import com.hightoon.controller.data.Signin
import com.hightoon.controller.data.User
import retrofit2.Call
import retrofit2.http.*

interface Services{

    @FormUrlEncoded
    @POST("/auth/signin")
    fun SignIn(@Field("id") id: String,
             @Field("passwd") passwd: String) : Call<Signin>

    @FormUrlEncoded
    @POST("/auth/signup")
    fun SignUp(@Field("id") id: String,
               @Field("passwd") passwd: String,
               @Field("name") name: String,
               @Field("phone") phone : String) : Call<User>

    @GET("/auto/{token}")
    fun Token(@Path("token") token: String): Call<MediaSession.Token>

    @Multipart
    @POST("/item/list")
    fun Season(@Part("season") season : String) : Call<SeasonList>
}