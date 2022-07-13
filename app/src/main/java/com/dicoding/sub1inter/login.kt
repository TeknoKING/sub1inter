package com.dicoding.intermedietsub1.API

import com.google.gson.annotations.SerializedName

data class LoginResult(

	@field:SerializedName("LoginRes")
	val LoginRes: List<Login>,)

data class Login(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String

	/*@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String*/
)
