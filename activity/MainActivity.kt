package com.example.sprapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appsrp.activity.activityrol
import com.example.appsrp.viewmodels.LoginViewModel
import com.example.sprapp.R
import com.example.sprapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var tvRegistrar: TextView
    private lateinit var btnLogin: Button
    private lateinit var etCedula: EditText
    private lateinit var etPassword: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Conecta los elementos del XML
        tvRegistrar = findViewById(R.id.tv_registrar)
        btnLogin = findViewById(R.id.btnLogin)
        etCedula = findViewById(R.id.etCedula)
        etPassword = findViewById(R.id.etPassword)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {
            val cedula = etCedula.text.toString()
            val password = etPassword.text.toString()

            if (cedula.isEmpty() || password.isEmpty() ){

                Toast.makeText(this, "ingresa cedula y contraseña", Toast.LENGTH_SHORT).show()
            }
            else{
                if (viewModel.validarLogin(cedula, password)){
                    Toast.makeText(this, "Login Exitoso", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Cedula o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }


        // Acciones
        tvRegistrar.setOnClickListener {
            val intent = Intent(this, activityrol::class.java)
            startActivity(intent)
        }



            // Aquí podrías validar el login
            if (cedula == cedula && password == password) {
                val intent = Intent(this, HomeContratista::class.java)
                startActivity(intent)
            } else {
                etPassword.error = "Credenciales incorrectas"
            }
        }

        // Ajustes visuales por sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
