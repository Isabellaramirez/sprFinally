package main.models.prestador

import main.models.sercat.EnumCategoria
import main.models.sercat.trabajos
import java.time.LocalDate

data class Prestador(
    var cedula: String,
    var nombre: String,
    var apellido: String,
    var direccion: String, // Cambiado de "ciudad" a "dirección"
    var celular: String,
    var correo: String,
    var fechaDeNacimiento: LocalDate,
    var contrasena: String,
    var categoria: EnumCategoria,
    var servicio: trabajos
)
