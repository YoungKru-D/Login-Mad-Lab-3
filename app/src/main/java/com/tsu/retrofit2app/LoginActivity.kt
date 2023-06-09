package com.tsu.retrofit2app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tsu.retrofit2app.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener{
            val username = binding.lUsername.text.toString()
            val password = binding.lPassword.text.toString()

            if (username.isEmpty()){
                val binuser = binding.lUsername
                binuser.error = "User required"
                binuser.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()){
                val binpass = binding.lPassword
                binpass.error = "User required"
                binpass.requestFocus()
                return@setOnClickListener
            }
            Log.e("LoginActivity", "Masuk 1", )
            val user= User(username, password)
            ApiConfig.instance.userLogin(user)
                .enqueue(object: Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if(response.isSuccessful){
                            Log.e("LoginActivity", "Masuk 2", )
                            Toast.makeText(this@LoginActivity, "Login berhasil",
                                Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()

                    }
                })
        }
    }
}