package com.example.srpapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.InputQueue
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sprapp.R
import com.example.sprapp.activity.HomeContratista
import main.models.prestador.LogicaPrestador
import main.models.prestador.Prestador
import main.models.sercat.EnumCategoria
import main.models.sercat.trabajos
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RegistroActivity : AppCompatActivity() {

    private val listaPrestadores = mutableListOf<Prestador>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnRegistrarPrestador = findViewById<Button>(R.id.btnregistrarme)
        btnRegistrarPrestador.setOnClickListener{
            if (validarCampos()){
                registraPrestador()
            }
        }

    }
    private fun validarCampos(): Boolean {
        val password = findViewById<EditText>(R.id.contraseña).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.confirmarcontraseña).text.toString()

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registraPrestador(){
        try {


        //obtener datos del formulario

        val cedula = findViewById<EditText>(R.id.cedula).text.toString()
        val nombre = findViewById<EditText>(R.id.nombres).text.toString()
        val apellido = findViewById<EditText>(R.id.apellidos).text.toString()
        val direccion = findViewById<EditText>(R.id.cedula).text.toString()
        val celular = findViewById<EditText>(R.id.celular).text.toString()
        val correo = findViewById<EditText>(R.id.correo).text.toString()
        val fechaNacimiento = LocalDate.parse(
            findViewById<EditText>(R.id.fechaNacimiento).text.toString(),
            DateTimeFormatter.ISO_DATE)
        val contrasena = findViewById<EditText>(R.id.contraseña).text.toString()

        val prestadorRegistrado = LogicaPrestador.crearPrestador(
            listaPrestadores = listaPrestadores,
            cedula = cedula,
            nombre = nombre,
            apellido = apellido,
            direccion = direccion,
            celular = celular,
            correo = correo,
            fechaDeNacimiento = fechaNacimiento,
            contrasena = contrasena,
            categoria = EnumCategoria.Hogar,
            servicio = trabajos.limpieza
        )

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, HomeContratista::class.java))
        finish()
        }catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
        }
}
