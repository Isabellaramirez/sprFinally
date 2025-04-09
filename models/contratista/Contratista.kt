package main.models.contratista

import java.time.LocalDate

data class Contratista(
    val cedula: String,
    var nombre: String,
    var apellido: String,
    var direccion: String,
    var celular: String,
    var correo: String,
    var fechaDeNacimiento: LocalDate,
    var contrasena: String
)
