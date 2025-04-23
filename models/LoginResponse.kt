package com.example.appsrp.models

import android.adservices.adid.AdId
import com.example.appsrp.models.rol.RolTipo
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val token: String?,  // Token JWT
    @SerializedName("rol") val rolId: Int,           // ID del rol
    @SerializedName("mensaje") val mensaje: String? // Mensaje opcional
)
