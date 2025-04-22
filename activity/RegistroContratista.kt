package com.example.appsrp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.RegisterResponse
import com.example.sprapp.R
import com.example.sprapp.activity.HomeContratista
import main.models.contratista.Contratista
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroContratistaActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_contratista)

        val btnCrearContratista = findViewById<Button>(R.id.btnCrearContratista)

        btnCrearContratista.setOnClickListener {
            if (validarCampos()) {
                registrarContratista()
            }
        }
    }

    private fun validarCampos(): Boolean {
        val password = findViewById<EditText>(R.id.contraseña).text.toString()
        val confirPassword = findViewById<EditText>(R.id.confirmarcontraseña).text.toString()

        if (password != confirPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registrarContratista() {
        try {
            val cedula = findViewById<EditText>(R.id.etCedula).text.toString()
            val nombres = findViewById<EditText>(R.id.etNombre).text.toString()
            val apellidos = findViewById<EditText>(R.id.etApellido).text.toString()
            val direccion = findViewById<EditText>(R.id.etDireccion).text.toString()
            val telefono = findViewById<EditText>(R.id.etCelular).text.toString()
            val correo = findViewById<EditText>(R.id.etCorreo).text.toString()
            val contrasena = findViewById<EditText>(R.id.contraseña).text.toString()
            val fechaNacimiento = findViewById<EditText>(R.id.fechaNacimiento).text.toString()

            // Foto por defecto (puedes cambiarlo si usas carga real de imágenes)
            val foto = "default.jpg"

            val contratista = Contratista(
                cedula = cedula,
                nombres = nombres,
                apellidos = apellidos,
                telefono = telefono,
                direccion = direccion,
                correo = correo,
                fechaNacimiento = fechaNacimiento, // debe ser en formato yyyy-MM-dd
                contrasena = contrasena,
                foto = foto,
                idRol = 1
            )

            val call = RetrofitInstance.apiService.registrarContratista(contratista)

            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@RegistroContratistaActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegistroContratistaActivity, HomeContratista::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegistroContratistaActivity,
                            "Error: ${response.body()?.message ?: "No se pudo registrar"}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegistroContratistaActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
