package com.example.appsrp.viewmodels
import android.util.Log

import android.content.Context
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.LoginResponse
import com.example.appsrp.models.rol.RolTipo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class LoginViewModel : ViewModel() {

    fun iniciarSesion(
        correo: String,
        contrasena: String,
        context: Context,
        onResult: (Boolean, Int?) -> Unit
    ) {
        val call = RetrofitInstance.apiService.iniciarSesion(
            mapOf(
                "correo" to correo,
                "contrasena" to contrasena
            )
        )

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        // Debug: Imprime la respuesta completa
                        Log.d("LOGIN_DEBUG", "Respuesta exitosa: $loginResponse")

                        // Guardar token y rol
                        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE).edit {
                            putString("token", loginResponse.token)
                            putInt("rol", loginResponse.rolId)
                            apply()
                        }
                        onResult(true, loginResponse.rolId)
                    } ?: run {
                        Log.e("LOGIN_ERROR", "Respuesta exitosa pero body es nulo")
                        onResult(false, null)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("LOGIN_ERROR", "Error en respuesta: $errorBody")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LOGIN_ERROR", "Fallo de red: ${t.message}")
                onResult(false, null)
            }
        })
    }
}