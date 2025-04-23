package com.example.appsrp
object CategoriaMapper {
    private val servicioToIdMap = mapOf(
        "AseoGeneral" to "1",
        "LimpiezaVidrios" to "2",
        "Carpinteria" to "3",
        "Plomeria" to "4",
        "PaseadorCanino" to "5",
        "BañoMascotas" to "6",
        "PeluqueroHombre" to "7",
        "PeluqueroMujer" to "8"
    )

    fun obtenerIdServicio(servicio: String): String {
        return servicioToIdMap[servicio]
            ?: throw IllegalArgumentException("Servicio no válido: $servicio")
    }
}