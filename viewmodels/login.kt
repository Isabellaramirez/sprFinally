package com.example.appsrp.viewmodels
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import main.models.contratista.Contratista
import main.models.prestador.Prestador
import main.models.sercat.EnumCategoria
import main.models.sercat.trabajos
import java.time.LocalDate

class LoginViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val usuariosMock = listOf(
        Contratista(
            cedula = "123",
            nombre = "Ana",
            apellido = "Pérez",
            direccion = "Calle 123",
            celular = "3001112222",
            correo = "ana@ejemplo.com",
            fechaDeNacimiento = LocalDate.now(),
            contrasena = "1234"
        ),
        Prestador(
            cedula = "456",
            nombre = "Carlos",
            apellido = "Gómez",
            direccion = "Carrera 456",
            celular = "3003334444",
            correo = "carlos@ejemplo.com",
            fechaDeNacimiento = LocalDate.now(),
            contrasena = "1234",
            categoria = EnumCategoria.Hogar,
            servicio = trabajos.Limpieza
        )
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun validarLogin(cedula: String, password: String): Boolean {
        return usuariosMock.any { usuario ->
            when (usuario) {
                is Contratista -> usuario.cedula == cedula && password == "1234"
                is Prestador -> usuario.cedula == cedula && password == "1234"
                else -> false
            }
        }
    }
}