package main.models.prestador

import main.models.sercat.EnumCategoria
import main.models.sercat.trabajos
import java.time.LocalDate

class LogicaPrestador {
    companion object {
        fun crearPrestador(
            listaPrestadores: MutableList<Prestador>,
            cedula: String,
            nombre: String,
            apellido: String,
            direccion: String,
            celular: String,
            correo: String,
            fechaDeNacimiento: LocalDate,
            contrasena: String, // Nuevo parámetro
            categoria: EnumCategoria,
            servicio: trabajos
        ): Prestador {
            val nuevoPrestador = Prestador(
                cedula = cedula,
                nombre = nombre,
                apellido = apellido,
                direccion = direccion,
                celular = celular,
                correo = correo,
                fechaDeNacimiento = fechaDeNacimiento,
                contrasena = contrasena, // Asignación del campo
                categoria = categoria,
                servicio = servicio
            )
            listaPrestadores.add(nuevoPrestador)
            return nuevoPrestador
        }

        fun actualizarPrestador(
            listaPrestadores: MutableList<Prestador>,
            cedula: String,
            nombre: String?,
            apellido: String?,
            direccion: String?,
            celular: String?,
            correo: String?,
            fechaDeNacimiento: LocalDate?,
            contrasena: String?, // Nuevo parámetro opcional
            categoria: EnumCategoria?,
            servicio: trabajos?
        ): Prestador {
            val prestador = listaPrestadores.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula")

            nombre?.let { prestador.nombre = it }
            apellido?.let { prestador.apellido = it }
            direccion?.let { prestador.direccion = it }
            celular?.let { prestador.celular = it }
            correo?.let { prestador.correo = it }
            fechaDeNacimiento?.let { prestador.fechaDeNacimiento = it }
            contrasena?.let { prestador.contrasena = it } // Actualización opcional
            categoria?.let { prestador.categoria = it }
            servicio?.let { prestador.servicio = it }

            return prestador
        }

        // Resto de métodos (eliminar, listar, buscar) permanecen igual
        fun eliminarPrestador(cedula: String, listaPrestadores: MutableList<Prestador>) {
            val prestador = listaPrestadores.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula")
            listaPrestadores.remove(prestador)
        }

        fun listarPrestador(listaPrestadores: MutableList<Prestador>): MutableList<Prestador> {
            return listaPrestadores
        }

        fun prestadorPorId(listaPrestadores: MutableList<Prestador>, cedula: String): Prestador {
            return listaPrestadores.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No encontrado")
        }
    }
}