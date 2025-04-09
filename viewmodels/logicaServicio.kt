package main.models.sercat

import kotlin.NoSuchElementException

class LogicaServicio {

    private val servicios: MutableList<Servicios> = mutableListOf()

    fun crearServicio(
        id: String,
        nombreServicio: trabajos,
        descripcionServicio: String,
        cedulaPrestador: String,
        categoria: EnumCategoria
    ): Servicios {
        val servicio = Servicios(id, nombreServicio, descripcionServicio, cedulaPrestador, categoria)
        servicios.add(servicio)
        return servicio
    }

    fun actualizarServicio(
        id: String,
        cedulaPrestador: String? = null,
        nombreServicio: trabajos? = null,
        descripcionServicio: String? = null,
        categoria: EnumCategoria? = null
    ): Servicios {
        val servicio = servicios.find { it.id == id } ?: throw NoSuchElementException("Servicio no encontrado")

        // Solo actualizar si hay valores nuevos
        cedulaPrestador?.let { servicio.cedulaPrestador = it }
        nombreServicio?.let { servicio.nombreServicio = it }
        descripcionServicio?.let { servicio.descripcionServicio = it }
        categoria?.let { servicio.categoria = it }

        return servicio
    }

    fun eliminarServicio(id: String) {
        val servicio = servicios.find { it.id == id } ?: throw NoSuchElementException("ID no encontrado")
        servicios.remove(servicio)
    }

    fun listarServicios(): List<Servicios> {
        return servicios.toList()
    }

    fun buscarServicioPorId(id: String): Servicios {
        return servicios.find { it.id == id } ?: throw NoSuchElementException("Servicio no encontrado")
    }

    fun filtrarServiciosPorCategoria(categoria: EnumCategoria): List<Servicios> {
        return servicios.filter { it.categoria == categoria }
    }
}
