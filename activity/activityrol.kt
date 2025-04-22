package com.example.appsrp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprapp.R
import com.example.sprapp.activity.HomeContratista
import com.example.srpapp.activity.RegistroActivity

class activityrol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activityrol)

        val btnContratista: Button = findViewById(R.id.btnContratista)
        val btnPrestador: Button = findViewById(R.id.btnPrestador)

        btnContratista.setOnClickListener {
            val intent = Intent(this, RegistroContratistaActivity::class.java)
            startActivity(intent)
        }

        btnPrestador.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}