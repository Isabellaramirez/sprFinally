package com.example.sprapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprapp.models.enums.EnumCategoria
import com.example.sprapp.models.enums.trabajos
import main.models.sercat.Servicios
//SE CREA UNA CLASE QUE HEREDA DE VIEWMODEL QUE ES UNA DEPENDENCIA QUE ESTA EN BUILD.GREDLET (ALGO ASI)
class ServiciosViewModel : ViewModel(){
    //MUTABLELIVEDATA: SIGNIFICA QUE ES UNA LISTA DE SERVICIOS
    //QUE ES UNA LISTA DE SERVICIOS QUE PUEDE CAMBIAR Y NOTIFICAR
    //A LOS OBSERVADORES CUANDO SE MODIFICA
    private val _servicios = MutableLiveData<MutableList<Servicios>>()
    //SE MUESTRA UNA VERSION DE LA LISTA PERO DE SOLO LECTURA
    //PARA QUE LA UI PUEDA OBSERVAR CAMBIOS SIN MODIFICAR LA LISTA
    val servicios: LiveData<MutableList<Servicios>> get() = _servicios
    //SE INICIALIZA _SERVICIOS CON UNA LISTA VACIA CUANDO SE CREA
    //VIEWMODEL
    init {
        _servicios.value = mutableListOf()
    }

    fun crearServicio(
        id: String,
        nombreServicio: trabajos,
        descripcionServicio: String,
        cedulaPrestador: String,
        categoria: EnumCategoria
    ){
        val nuevoServicio = Servicios(
            id,
            nombreServicio,
            descripcionServicio,
            cedulaPrestador,
            categoria
        )
        //SE USA ? PARA ASEGURARSE QUE LA LISTA NO SEA NULL
        //SE USA _SERVICIOS PARA NOTIFICAR A LOS OBSERVADORES QUE
        //LA LISTA A CAMBIADO
        _servicios.value?.add(nuevoServicio)
    }

    fun actualizarServicio(
        id: String,
        nombreServicio: trabajos? = null,
        descripcionServicio: String? = null,
        cedulaPrestador: String? = null,
        categoria: EnumCategoria? = null
    ){
        val servicioActualizado = _servicios.value?.find { it.id == id }?: throw NoSuchElementException("Servicio no encontrado")
        // ?: se usa por si no se porporciona un valor nuevo
        //que mantenga el anterior
        servicioActualizado.nombreServicio = nombreServicio ?: servicioActualizado.nombreServicio
        servicioActualizado.descripcionServicio = descripcionServicio ?: servicioActualizado.descripcionServicio
        servicioActualizado.cedulaPrestador = cedulaPrestador ?: servicioActualizado.cedulaPrestador
        servicioActualizado.categoria = categoria ?: servicioActualizado.categoria

        _servicios.value = _servicios.value
    }

    fun eliminarServicio(id : String){
        //=! se filtra la lista para excluir al servicio con el id proporcionado
        _servicios.value = _servicios.value?.filter {it.id != id}?.toMutableList()
    }

    fun buscarServicioPorId(id: String): Servicios{
        return _servicios.value?.find{it.id == id}?: throw NoSuchElementException("servicio no encontrado")
    }
}