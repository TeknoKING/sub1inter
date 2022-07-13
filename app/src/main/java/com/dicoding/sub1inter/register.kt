package com.dicoding.intermedietsub1

import com.google.gson.annotations.SerializedName
data class RegisterResult(

	@field:SerializedName("registerRes")
	val registerRes: List<Register>
	)

data class Register(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
