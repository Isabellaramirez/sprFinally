package com.example.appsrp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sprapp.R
import com.example.sprapp.activity.HomeContratista
import main.models.contratista.Contratista
import main.models.contratista.LogicaContratista
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegistroContratista : AppCompatActivity() {

    private val listaContratistas = mutableListOf<Contratista>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro_contratista)

        val btnCrearContratista = findViewById<Button>(R.id.btnCrearContratista)

        btnCrearContratista.setOnClickListener{
            if (validarCampos()){
                registraContratista()
            }
        }
    }

    private fun validarCampos():Boolean{

        val password = findViewById<EditText>(R.id.contraseña).text.toString()
        val confirPassword = findViewById<EditText>(R.id.confirmarcontraseña).text.toString()

        if (password != confirPassword){
            Toast.makeText( this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registraContratista(){
        try {
            val cedula = findViewById<EditText>(R.id.etCedula).text.toString()
            val nombre = findViewById<EditText>(R.id.etNombre).text.toString()
            val apellido = findViewById<EditText>(R.id.etApellido).text.toString()
            val direccion = findViewById<EditText>(R.id.etDireccion).text.toString()
            val celular = findViewById<EditText>(R.id.etCelular).text.toString()
            val correo = findViewById<EditText>(R.id.etCorreo).text.toString()
            val contrasena = findViewById<EditText>(R.id.contraseña).text.toString()
            val fechaNacimiento = LocalDate.parse(
                findViewById<EditText>(R.id.fechaNacimiento).text.toString(),
                DateTimeFormatter.ISO_DATE)
            val contratistaRegistrado = LogicaContratista.crearContratista(

                listaContratistas = listaContratistas,
                cedula = cedula,
                nombre = nombre,
                apellido = apellido,
                direccion = direccion,
                celular = celular,
                correo = correo,
                fechaDeNacimiento = fechaNacimiento,
                contrasena = contrasena,

            )
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeContratista::class.java))
            finish()
        }catch (e: Exception){
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }








}


