package com.tsu.retrofit2app

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("account/login")
    fun userLogin(
        @Body user: User
    ): Call<LoginResponse>
}