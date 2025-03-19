package com.example.sprapp.ui
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprapp.R
import com.example.sprapp.viewmodels.PrestadorViewModel
import com.example.sprapp.models.enums.EnumCategoria
import com.example.sprapp.models.enums.trabajos
import java.time.DateTimeException
import java.time.LocalDate

class PrestadorActivity : AppCompatActivity() {
    private val prestadorViewModel: PrestadorViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prestador)
        //configura el padding para evitar superposiciones
        //con la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //se referencia a los elementos de la ui
        val etCedula = findViewById<EditText>(R.id.etCedula)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.Apellido)
        val etCiudad = findViewById<EditText>(R.id.etCiudad)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etFechaNacimiento = findViewById<EditText>(R.id.etFechaNacimiento)
        val etCategoria = findViewById<EditText>(R.id.etCategoria)
        val etServicio = findViewById<EditText>(R.id.Servicio)
        val btnCrearPerfil = findViewById<Button>(R.id.btnCrearPerfilPrestador)

        //configurar el listener del boton
        //cuando se da clic se define qeu sucede
        btnCrearPerfil.setOnClickListener {
            //se obtienen los datos del usuario
            val cedula = etCedula.text.toString()
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val ciudad = etCiudad.text.toString()
            val celular = etCelular.text.toString()
            val correo = etCorreo.text.toString()

            // Obtener el texto del campo de fecha
            val fechaTexto = etFechaNacimiento.text.toString()

// Verificar si el campo está vacío
            if (fechaTexto.isEmpty()) {
                Toast.makeText(this, "La fecha de nacimiento no puede estar vacía", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

// Validar el formato de la fecha con una expresión regular
            val fechaPattern = Regex("\\d{4}-\\d{2}-\\d{2}")
            if (!fechaPattern.matches(fechaTexto)) {
                Toast.makeText(this, "Formato de fecha inválido. Use yyyy-MM-dd", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

// Parsear la fecha de nacimiento
            val fechaNacimiento = try {
                LocalDate.parse(fechaTexto) // Intenta parsear la fecha
            } catch (e: DateTimeException) {
                // Si el formato es incorrecto, muestra un mensaje de error
                Toast.makeText(this, "Formato de fecha inválido. Use yyyy-MM-dd", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val categoria = EnumCategoria.valueOf(etCategoria.text.toString())
            val servicio = trabajos.valueOf(etServicio.text.toString())

            prestadorViewModel.crearPrestador(
                cedula,
                nombre,
                apellido,
                ciudad,
                celular,
                correo,
                fechaNacimiento,
                categoria,
                servicio
            )
            //los cuadros de mensajes emergentes
            Toast.makeText(this, "Prestador Creado", Toast.LENGTH_SHORT).show()
        }


    }
}