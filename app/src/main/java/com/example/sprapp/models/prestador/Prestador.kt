package main.models.prestador
import com.example.sprapp.models.enums.EnumCategoria
import com.example.sprapp.models.enums.trabajos

import java.time.LocalDate

data class Prestador(
    var cedula: String,
    var nombre: String,
    var apellido: String,
    var ciudad: String,
    var celular: String,
    var correo: String,
    var fechaDeNacimiento: LocalDate,
    var categoria: EnumCategoria,
    var servicio: trabajos
){

}