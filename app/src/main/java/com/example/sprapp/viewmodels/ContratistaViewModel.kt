package com.example.sprapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import main.models.contratista.Contratista
import java.time.LocalDate

class ContratistaViewModel: ViewModel() {
//se crea variable privada para cambios, actualizaciones y envio de notificaciones
    private val _contratista = MutableLiveData<MutableList<Contratista>>()
// se crea variable publica para mostrar la lista pero que no pueda ser modificacda
    val contratista : LiveData<MutableList<Contratista>> get() = _contratista

    init {
        _contratista.value = mutableListOf()
    }
    fun crearContratista(
        cedula: String,
        nombre: String,
        apellido: String,
        ciudad: String,
        celular: String,
        correo: String,
        fechaDeNacimiento: LocalDate
    ){
        val contratistaNuevo = Contratista(
            cedula,
            nombre,
            apellido,
            ciudad,
            celular,
            correo,
            fechaDeNacimiento
        )
        //siempre se debe de poner le ? de lo contrario no funcionara
        _contratista.value?.add(contratistaNuevo)
    }

    fun actualizarContratista(
        cedula: String,
        nombre: String ? = null,
        apellido: String ? = null,
        ciudad: String ? = null,
        celular: String ? = null,
        correo: String ? = null,
        fechaDeNacimiento: LocalDate ? = null
    ){
        val cedulaActualizar = _contratista.value?.find { it.cedula == cedula } ?: throw NoSuchElementException("Cedula no encontrada")

        cedulaActualizar.nombre = nombre ?: cedulaActualizar.nombre
        cedulaActualizar.apellido = apellido ?: cedulaActualizar.apellido
        cedulaActualizar.ciudad =ciudad ?: cedulaActualizar.ciudad
        cedulaActualizar.celular = celular ?: cedulaActualizar.celular
        cedulaActualizar.correo = correo ?: cedulaActualizar.correo
        cedulaActualizar.fechaDeNacimiento = fechaDeNacimiento ?: cedulaActualizar.fechaDeNacimiento

        _contratista.value = _contratista.value
    }

    fun elïminarContratista(cedula: String){
        _contratista.value = contratista.value?.filter { it.cedula!=cedula }?.toMutableList()
    }

    fun buscarContratistaPorCedula(cedula: String): Contratista {
        return _contratista.value?.find { it.cedula == cedula }?: throw NoSuchElementException("Cedula no encontrada")
    }
}