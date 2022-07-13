package com.dicoding.sub1inter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sub1inter.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UserViewModel::class.java]


        userViewModel.isloading.observe(this) {
            showLoading(it)
        }
        login()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }



    fun login (){
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if(email.isEmpty()){
            binding.etEmail.error = "Kolom ini tidak boleh kosong"
            binding.etEmail.requestFocus()
            return
        }
        else if(password.isEmpty()){
            binding.etPassword.error = "Kolom ini tidak boleh kosong"
            binding.etPassword.requestFocus()
            return
        }
        ApiConfig.getApiService().loginRequest( email, password)

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            userViewModel.UserLogin( email, password)
            Log.d("Sucsses","test")
        }
    }
    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}