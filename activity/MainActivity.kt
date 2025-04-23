package com.example.sprapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsrp.activity.HomePrestador
import com.example.appsrp.activity.activityrol
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.rol.RolTipo
import com.example.appsrp.viewmodels.LoginViewModel
import com.example.sprapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var tvRegistrar: TextView
    private lateinit var btnLogin: Button
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText

    private val api = RetrofitInstance.apiService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Conectar vistas
        tvRegistrar = findViewById(R.id.tv_registrar)
        btnLogin = findViewById(R.id.btnLogin)
        etCorreo = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        // Acción de Login
        btnLogin.setOnClickListener {
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                login(correo, password)
            }
        }

        // Ir a pantalla de registro
        tvRegistrar.setOnClickListener {
            val intent = Intent(this, activityrol::class.java)
            startActivity(intent)
        }

        // Ajustes visuales del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun login(correo: String, contrasena: String) {
        val viewModel = LoginViewModel()

        viewModel.iniciarSesion(correo, contrasena, this) { success, rolId ->
            if (success) {
                val rolTipo = RolTipo.fromId(rolId)

                when (rolTipo) {
                    RolTipo.CONTRATISTA -> {
                        startActivity(Intent(this, HomeContratista::class.java))
                        finish()
                    }
                    RolTipo.PRESTADOR -> {
                        startActivity(Intent(this, HomePrestador::class.java))
                        finish()
                    }
                    else -> {
                        Toast.makeText(this,
                            "Error: Rol ${rolId ?: "null"} no reconocido",
                            Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
        }


