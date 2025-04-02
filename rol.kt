package com.example.appsrp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class rol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rol)

        val spinner: Spinner = findViewById(R.id.rol)

        val tipos = listOf("Trabador", "Cliente")  //listaa que se muestra en el spinner
        //arrayadapter es el puente entre los datos que es la lista y la vista que es el spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,tipos)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        // OnItemSelect se ejecuta cuando un usuario selecciona un item
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()

                // Mostrar un Toast con el valor seleccionado
                Toast.makeText(this@rol, "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()

                // Aquí puedes manejar lo que suceda según la selección
                if (selectedItem == "Prestador de servicio") {
                    val intent = Intent(this@rol, registro::class.java)
                    startActivity(intent)
                    finish()
                } else if (selectedItem == "Contratista") {
                    val intent = Intent(this@rol, registroContratista::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Acción cuando no se ha seleccionado nada (opcional)
            }
        })




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}