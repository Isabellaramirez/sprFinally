package com.example.appsrp.api

import com.example.appsrp.models.LoginResponse
import com.example.appsrp.models.RegisterResponse
import main.models.contratista.Contratista
import main.models.prestador.Prestador
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @Multipart  // Cambiado a multipart
    @POST("/signin")
    fun registrarPrestador(
        @Part("cedula") cedula: RequestBody,
        @Part("nombres") nombres: RequestBody,
        @Part("apellidos") apellidos: RequestBody,
        @Part("celular") celular: RequestBody,
        @Part("direccion") direccion: RequestBody,
        @Part("contrasena") contrasena: RequestBody,
        @Part("titulos_uni") titulosUni: RequestBody,
        @Part("descripcion") descripcion: RequestBody,
        @Part("correo") correo: RequestBody,
        @Part("fecha_nacimiento") fechaNacimiento: RequestBody,
        @Part("id_rol") idRol: RequestBody,
        @Part foto: MultipartBody.Part?,
        @Part("categoria") categoria: RequestBody
    ): Call<RegisterResponse>

    @POST("/signin")
    fun registrarContratista(@Body contratista: Contratista): Call<RegisterResponse>

    @POST("/login")
    fun iniciarSesion(@Body credenciales: Map<String, String>): Call<LoginResponse>

}

