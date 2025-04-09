package main.models.contratista

import java.time.LocalDate

class LogicaContratista {
    companion object {
        fun crearContratista(
            listaContratistas: MutableList<Contratista>,
            cedula: String,
            nombre: String,
            apellido: String,
            direccion: String,
            celular: String,
            correo: String,
            fechaDeNacimiento: LocalDate,
            contrasena: String // Nuevo parámetro
        ): Contratista {
            val contratista = Contratista(
                cedula = cedula,
                nombre = nombre,
                apellido = apellido,
                direccion = direccion,
                celular = celular,
                correo = correo,
                fechaDeNacimiento = fechaDeNacimiento,
                contrasena = contrasena // Asignación del campo
            )
            listaContratistas.add(contratista)
            return contratista
        }

        fun actualizarContratista(
            listaContratistas: MutableList<Contratista>,
            cedula: String,
            nombre: String?,
            apellido: String?,
            direccion: String?,
            celular: String?,
            correo: String?,
            fechaDeNacimiento: LocalDate?,
            contrasena: String? // Nuevo parámetro opcional
        ): Contratista {
            val contratista = listaContratistas.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula del contratista")

            nombre?.let { contratista.nombre = it }
            apellido?.let { contratista.apellido = it }
            direccion?.let { contratista.direccion = it }
            celular?.let { contratista.celular = it }
            correo?.let { contratista.correo = it }
            fechaDeNacimiento?.let { contratista.fechaDeNacimiento = it }
            contrasena?.let { contratista.contrasena = it } // Actualización opcional

            return contratista
        }

        // Resto de métodos (eliminar, listar, buscar) permanecen igual
        fun eliminarContratista(cedula: String, listaContratistas: MutableList<Contratista>) {
            val contratista = listaContratistas.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula")
            listaContratistas.remove(contratista)
        }

        fun listarContratistas(listaContratistas: MutableList<Contratista>): List<Contratista> {
            return listaContratistas.toList()
        }

        fun contratistaPorId(listaContratistas: MutableList<Contratista>, cedula: String): Contratista? {
            return listaContratistas.find { it.cedula == cedula }
        }
    }
}