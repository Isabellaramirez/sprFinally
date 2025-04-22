package com.example.appsrp.models

data class RegisterResponse (
    val success: Boolean,
    val message: String,
    val token: String?
){
}