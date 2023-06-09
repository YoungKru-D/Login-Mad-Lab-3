package com.tsu.retrofit2app

data class User (val username:String,val password: String)

data class LoginResponse(val error:Boolean, val message: String, val user: User)