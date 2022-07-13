package com.dicoding.sub1inter


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.dicoding.sub1inter.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityRegisterBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UserViewModel::class.java]


        userViewModel.isloading.observe(this) {
            showLoading(it)
        }
        register()


    }

    private fun showLoading(isLoading: Boolean){
        Binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    fun register (){
        val email = Binding.etEmail.text.toString().trim()
        val password = Binding.etPassword.text.toString().trim()
        val name = Binding.etNama.text.toString().trim()


        if(name.isEmpty()){
            Binding.etNama.error = "Kolom ini tidak boleh kosong"
            Binding.etNama.requestFocus()
            return
        }
        else if(email.isEmpty()){
            Binding.etEmail.error = "Kolom ini tidak boleh kosong"
            Binding.etEmail.requestFocus()
            return
        }
        else if(password.isEmpty()){
            Binding.etPassword.error = "Kolom ini tidak boleh kosong"
            Binding.etPassword.requestFocus()
            return
        }
        ApiConfig.getApiService().registerRequest(name, email, password)


        Binding.btnRegister.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            userViewModel.UserRegister(name, email, password)
        }
    }
}