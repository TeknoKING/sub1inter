package com.dicoding.sub1inter

import com.dicoding.intermedietsub1.API.Login
import com.dicoding.intermedietsub1.API.LoginResult
import com.dicoding.intermedietsub1.Register
import com.dicoding.intermedietsub1.RegisterResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun loginRequest(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResult>

    @FormUrlEncoded
    @POST("register")
    fun registerRequest(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResult>
}