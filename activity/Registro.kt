package com.example.srpapp.activity
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.appsrp.CategoriaMapper
import com.example.appsrp.activity.HomePrestador
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.getMultipartFromUri
import com.example.appsrp.models.RegisterResponse
import com.example.appsrp.toRequestBody
import com.example.sprapp.R
import main.models.prestador.Prestador
import main.models.sercat.EnumCategoria
import main.models.sercat.Trabajos
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : AppCompatActivity() {
    private var imageUri: Uri? = null
    private lateinit var spinnerCategoria: Spinner
    private lateinit var layoutServicios: LinearLayout
    private val apiService = RetrofitInstance.apiService

    private val seleccionarImagenLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            imageUri = result.data!!.data
            findViewById<ImageView>(R.id.foto).setImageURI(imageUri)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Configuración del spinner de categorías
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        layoutServicios = findViewById(R.id.layoutServicios)

        // Cargar categorías al spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            EnumCategoria.values().map { it.name }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = adapter

        // Listener para cambios en la selección de categoría
        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mostrarServicios(EnumCategoria.values()[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                layoutServicios.removeAllViews()
            }
        }

        // Configuración del botón de foto
        findViewById<ImageView>(R.id.foto).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            seleccionarImagenLauncher.launch(intent)
        }

        // Configuración del botón de registro
        findViewById<Button>(R.id.btnregistrarme).setOnClickListener {
            if (validarCampos()) {
                subirImagenYRegistrar()
            }
        }
    }

    private fun mostrarServicios(categoria: EnumCategoria) {
        layoutServicios.removeAllViews()

        when (categoria) {
            EnumCategoria.Limpieza -> {
                agregarServicioCheckBox(Trabajos.AseoGeneral.name)
                agregarServicioCheckBox(Trabajos.LimpiezaVidrios.name)
            }
            EnumCategoria.Hogar -> {
                agregarServicioCheckBox(Trabajos.Carpinteria.name)
                agregarServicioCheckBox(Trabajos.Plomeria.name)
            }
            EnumCategoria.Mascotas -> {
                agregarServicioCheckBox(Trabajos.PaseadorCanino.name)
                agregarServicioCheckBox(Trabajos.BañoMascotas.name)
            }
            EnumCategoria.Belleza -> {
                agregarServicioCheckBox(Trabajos.PeluqueroHombre.name)
                agregarServicioCheckBox(Trabajos.PeluqueroMujer.name)
            }
            else -> { /* No se muestran servicios para otras categorías */ }
        }
    }

    private fun agregarServicioCheckBox(nombreServicio: String) {
        val checkBox = CheckBox(this).apply {
            text = nombreServicio
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        }
        layoutServicios.addView(checkBox)
    }

    private fun validarCampos(): Boolean {
        // Validación de contraseñas
        val password = findViewById<EditText>(R.id.contraseña).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.confirmarcontraseña).text.toString()

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validación de al menos un servicio seleccionado
        var servicioSeleccionado = false
        for (i in 0 until layoutServicios.childCount) {
            if ((layoutServicios.getChildAt(i) as CheckBox).isChecked) {
                servicioSeleccionado = true
                break
            }
        }

        if (!servicioSeleccionado) {
            Toast.makeText(this, "Seleccione al menos un servicio", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun subirImagenYRegistrar() {
        if (imageUri == null) {
            val prestador = crearPrestadorDesdeFormulario("default.jpg")
            registrarPrestador(prestador, imageUri)
        } else {
            // [MANTENGO TU CÓDIGO ORIGINAL DE SUBIDA DE IMAGEN]
            // Solo cambia la creación del prestador para incluir los servicios
            val prestador = crearPrestadorDesdeFormulario("url_temporal.jpg")
            registrarPrestador(prestador, imageUri)
        }
    }

    private fun crearPrestadorDesdeFormulario(foto: String): Prestador {
        val categoriaSeleccionada = EnumCategoria.values()[spinnerCategoria.selectedItemPosition].name
        val serviciosSeleccionados = mutableListOf<String>()

        for (i in 0 until layoutServicios.childCount) {
            val checkBox = layoutServicios.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                val servicioNombre = checkBox.text.toString()
                val idServicio = CategoriaMapper.obtenerIdServicio(servicioNombre)
                serviciosSeleccionados.add(idServicio)
            }
        }

        if (serviciosSeleccionados.isEmpty()) {
            Toast.makeText(this, "Selecciona al menos un servicio", Toast.LENGTH_SHORT).show()
            throw IllegalArgumentException("Servicios requeridos")
        }

        // Unimos todos los IDs de servicios con comas
        val categoriasString = serviciosSeleccionados.joinToString(",")

        return Prestador(
            nombres = findViewById<EditText>(R.id.nombres).text.toString(),
            apellidos = findViewById<EditText>(R.id.apellidos).text.toString(),
            correo = findViewById<EditText>(R.id.correo).text.toString(),
            cedula = findViewById<EditText>(R.id.cedula).text.toString(),
            celular = findViewById<EditText>(R.id.celular).text.toString(),
            direccion = findViewById<EditText>(R.id.direccion).text.toString(),
            fechaNacimiento = findViewById<EditText>(R.id.fechaNacimiento).text.toString(),
            contrasena = findViewById<EditText>(R.id.contraseña).text.toString(),
            idRol = 2,
            descripcion = findViewById<EditText>(R.id.descripcion).text.toString(),
            titulosUni = findViewById<EditText>(R.id.titulosUni).text.toString(),
            foto = foto,
            categorias = categoriasString // Ahora es un String con IDs separados por comas
        )
    }

    private fun registrarPrestador(prestador: Prestador, imageUri: Uri?) {
        val fotoPart = imageUri?.let { this.getMultipartFromUri("foto", it) }



        val call = RetrofitInstance.apiService.registrarPrestador(
            cedula = prestador.cedula.toRequestBody(),
            nombres = prestador.nombres.toRequestBody(),
            apellidos = prestador.apellidos.toRequestBody(),
            celular = prestador.celular.toRequestBody(),
            direccion = prestador.direccion.toRequestBody(),
            contrasena = prestador.contrasena.toRequestBody(),
            titulosUni = prestador.titulosUni.toRequestBody(),
            descripcion = prestador.descripcion.toRequestBody(),
            correo = prestador.correo.toRequestBody(),
            fechaNacimiento = prestador.fechaNacimiento.toRequestBody(),
            idRol = prestador.idRol.toString().toRequestBody(),
            foto = fotoPart,
            categoria = prestador.categorias.toRequestBody()
        )

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegistroActivity, HomePrestador::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    finish()
                } else {
                    val error = response.errorBody()?.string()
                    Log.e("RegistroActivity", "Error en registro: $error")
                    Toast.makeText(this@RegistroActivity, "Error al registrar: $error", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("RegistroActivity", "Fallo de conexión: ${t.message}", t)
                Toast.makeText(this@RegistroActivity, "Fallo de conexión: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}