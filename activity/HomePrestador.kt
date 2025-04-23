package com.example.appsrp.activity

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprapp.R

class HomePrestador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_prestador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //obtener los datos del intent
        val nombre = intent.getStringExtra("nombre") ?: "Nombre del Prestador"
        val apellidos = intent.getStringExtra("apellidos") ?: ""
        val fotoUri = intent.getStringExtra("fotoUri")

        //configurar nombre
        val nombreCompleto = "$nombre $apellidos"
        findViewById<TextView>(R.id.nombreUsuario).text = nombreCompleto

        //configuracion de la foto
        fotoUri?.let {
            try {
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, Uri.parse(it)))
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(it))
                }
                findViewById<ImageView>(R.id.fotoPerfil).setImageBitmap(bitmap)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}