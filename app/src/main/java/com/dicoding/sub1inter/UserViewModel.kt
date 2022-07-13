package com.dicoding.sub1inter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.intermedietsub1.API.Login
import com.dicoding.intermedietsub1.API.LoginResult
import com.dicoding.intermedietsub1.Register

import com.dicoding.intermedietsub1.RegisterResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    private val _userregister = MutableLiveData<List<Register>>()
    val userregister: LiveData<List<Register>> = _userregister

    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _isMessage = MutableLiveData<String>()
    val isMessage: LiveData<String> = _isMessage

    private val _userlogin = MutableLiveData<List<Login>>()
    val userlogin: LiveData<List<Login>> = _userlogin

    companion object{
        private val TAG = "UserViewModel"
    }

    fun UserRegister(name: String, email: String, password: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().registerRequest(name, email, password)
        client.enqueue(object : Callback<RegisterResult> {
            override fun onResponse(
                call: Call<RegisterResult>,
                response: Response<RegisterResult>
            ) {
                _isloading.value = false
                _isError.value = !response.isSuccessful


                if (response.isSuccessful){
                    _isMessage.value = response.body()?.registerRes
                }else{
                    Log.e(TAG, "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResult>, t: Throwable) {
                _isloading.value = false
                Log.e(TAG, "Failure: ${t.message.toString()}")
            }
        })
    }

    fun UserLogin(email: String, password: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().loginRequest(email, password)
        client.enqueue(object : Callback<LoginResult> {
            override fun onResponse(
                call: Call<LoginResult>,
                response: Response<LoginResult>
            ) {
                _isloading.value = false
                _isError.value = !response.isSuccessful


                if (response.isSuccessful){
                    _userlogin.value = response.body()?.LoginRes
                    Log.d("Sucsses", _userlogin.value.toString())
                }else{
                    Log.e(TAG, "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                _isloading.value = false
                Log.e(TAG, "Failure: ${t.message.toString()}")
            }
        })
    }
}
