package com.example.sprapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsrp.activity.HomePrestador
import com.example.sprapp.R

class RolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activityrol)

        val btnContratista: Button = findViewById(R.id.btnContratista)
        val btnPrestador: Button = findViewById(R.id.btnPrestador)

        btnContratista.setOnClickListener {
            val intent = Intent(this, HomeContratista::class.java)
            startActivity(intent)
        }

        btnPrestador.setOnClickListener {
            val intent = Intent(this, HomePrestador::class.java)
            startActivity(intent)
        }
    }
}