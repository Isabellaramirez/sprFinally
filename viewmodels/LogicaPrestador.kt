package com.example.appsrp.viewmodels

import android.os.Build
import android.support.annotation.RequiresApi
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.RegisterResponse
import main.models.prestador.Prestador
import main.models.sercat.EnumCategoria
import main.models.sercat.Trabajos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.NoSuchElementException

class LogicaPrestador {
/*
    companion object {

        private val apiService = RetrofitInstance.apiService


        fun crearPrestador(
            listaPrestadores: MutableList<Prestador>,
            cedula: String,
            nombre: String,
            apellido: String,
            direccion: String,
            celular: String,
            correo: String,
            fechaDeNacimiento: Date?,
            contrasena: String,
            categoria: EnumCategoria,
            servicio: Trabajos,
            descripcion: String,
            titulosUni: String,
            foto: String
        ): Prestador {
            val imageUrl = "url_de_imagen_de_cloudinary"
            val nuevoPrestador = Prestador(
                cedula = cedula,
                nombres = nombre,
                apellidos = apellido,
                direccion = direccion,
                celular = celular,
                correo = correo,
                fechaNacimiento = fechaDeNacimiento.toString(),
                contrasena = contrasena,
                descripcion = descripcion,
                titulosUni = titulosUni,
                foto = imageUrl
            )
            listaPrestadores.add(nuevoPrestador)
            return nuevoPrestador
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun actualizarPrestador(
            listaPrestadores: MutableList<Prestador>,
            cedula: String,
            nombre: String?,
            apellido: String?,
            direccion: String?,
            celular: String?,
            correo: String?,
            fechaDeNacimiento: Date?,
            contrasena: String?,
            categoria: EnumCategoria?,
            servicio: Trabajos?,
            descripcion: String?,
            titulosUni: String?,
            foto: String?
        ): Prestador {
            val prestador = listaPrestadores.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula")

            nombre?.let { prestador.nombres = it }
            apellido?.let { prestador.apellidos = it }
            direccion?.let { prestador.direccion = it }
            celular?.let { prestador.celular = it }
            correo?.let { prestador.correo = it }
            fechaDeNacimiento?.let { prestador.fechaNacimiento = it.toString() }
            contrasena?.let { prestador.contrasena = it }
            descripcion?.let { prestador.descripcion = it }
            titulosUni?.let { prestador.titulosUni = it }
            foto?.let { prestador.foto = it }

            return prestador
        }

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

        fun crearMapaRegistroPrestador(prestador: Prestador): Map<String, Any?> {
            val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fechaFormateada = formatoFecha.format(prestador.fechaNacimiento ?: Date())

            return mapOf(
                "nombres" to prestador.nombres,
                "apellidos" to prestador.apellidos,
                "correo" to prestador.correo,
                "cedula" to prestador.cedula,
                "celular" to prestador.celular,
                "direccion" to prestador.direccion,
                "fecha_nacimiento" to fechaFormateada,
                "contrasena" to prestador.contrasena,
                "id_rol" to 2,
                "descripcion" to prestador.descripcion,
                "titulos_uni" to prestador.titulosUni,
                "foto" to prestador.foto
            )
        }

        fun registrarPrestador(prestador: Prestador, onResult: (Boolean) -> Unit) {
            val mapa = crearMapaRegistroPrestador(prestador)
            val call = apiService.registrarPrestador(mapa)
            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    onResult(response.isSuccessful)
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    onResult(false)
                }
            })
        }

    }

 */
}