package com.example.appsrp.viewmodels
import android.util.Log

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class LoginViewModel : ViewModel() {

    fun iniciarSesion(
        correo: String,
        contrasena: String,
        context: Context,
        onResult: (Boolean, Int?) -> Unit
    ) {
        val request = mapOf(
            "correo" to correo,
            "contrasena" to contrasena
        )

        val call = RetrofitInstance.apiService.iniciarSesion(request)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LoginDebug", "Respuesta completa: ${response.body()}")
                Log.d("LoginDebug", "Body completo: ${response.body()}")
                Log.d("LoginDebug", "ErrorBody: ${response.errorBody()?.string()}")

                if (response.isSuccessful && response.body() != null ) {

                    val loginResponse = response.body()!!
                    Log.d("LoginDebug", "Success: ${loginResponse.success}, Rol: ${loginResponse.rol}")

                    val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                    prefs.edit().putString("token", loginResponse.success).apply()
                    prefs.edit().putInt("rol", loginResponse.rol).apply()
                    Log.d("ROL_DEBUG", "Valor CRUDO del rol desde API: ${loginResponse.rol}")
                    onResult(true, loginResponse.rol)
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(false, null)
            }
        })
    }
}