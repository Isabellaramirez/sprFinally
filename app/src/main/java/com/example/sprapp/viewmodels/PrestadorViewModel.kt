package com.example.sprapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprapp.models.enums.EnumCategoria
import com.example.sprapp.models.enums.trabajos
import main.models.prestador.Prestador
import java.time.LocalDate

class PrestadorViewModel: ViewModel() {
    private val _Prestador = MutableLiveData<MutableList<Prestador>>()

    val prestador : LiveData<MutableList<Prestador>> get() = _Prestador

    init {
        _Prestador.value = mutableListOf()
    }
    fun crearPrestador(
        cedula: String,
        nombre: String,
        apellido: String,
        ciudad: String,
        celular: String,
        correo: String,
        fechaDeNacimiento: LocalDate,
        categoria: EnumCategoria,
        servicio: trabajos
    ){
        val PrestadorNuevo = Prestador(
            cedula,
            nombre,
            apellido,
            ciudad,
            celular,
            correo,
            fechaDeNacimiento,
            categoria,
            servicio
        )
        _Prestador.value?.add(PrestadorNuevo)
    }

    fun actualizarPrestador(
        cedula: String,
        nombre: String ?= null,
        apellido: String ?= null,
        ciudad: String ?= null,
        celular: String ?= null,
        correo: String ?= null,
        fechaDeNacimiento: LocalDate ?= null,
        categoria: EnumCategoria ?= null,
        servicio: trabajos ?= null
    ){
        val prestadorActualizar = _Prestador.value?.find { it.cedula == cedula }?:
        throw NoSuchElementException("Cedula no encontrada")

        prestadorActualizar.nombre = nombre ?: prestadorActualizar.nombre
        prestadorActualizar.apellido = apellido ?: prestadorActualizar.apellido
        prestadorActualizar.ciudad = ciudad ?: prestadorActualizar.ciudad
        prestadorActualizar.celular = celular ?: prestadorActualizar.celular
        prestadorActualizar.correo = correo ?: prestadorActualizar.correo
        prestadorActualizar.fechaDeNacimiento = fechaDeNacimiento ?: prestadorActualizar.fechaDeNacimiento
        prestadorActualizar.categoria = categoria ?: prestadorActualizar.categoria
        prestadorActualizar.servicio = servicio ?: prestadorActualizar.servicio
    }

    fun eliminarPrestador(cedula: String){
        _Prestador.value = _Prestador.value?.filter { it.cedula!=cedula }?.toMutableList()
    }

    fun buscarPrestadorPorCedula(cedula: String): Prestador{
        return _Prestador.value?.find { it.cedula==cedula }?: throw NoSuchElementException("Cedula no encontrada")
    }


}