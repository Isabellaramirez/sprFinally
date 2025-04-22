package main.models.sercat

data class Servicios(
    val id: String,
    var nombreServicio: Trabajos,
    var descripcionServicio: String,
    var cedulaPrestador: String,
    var categoria: EnumCategoria
)
