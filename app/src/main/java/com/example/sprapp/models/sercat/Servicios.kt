package main.models.sercat
import com.example.sprapp.models.enums.trabajos
import com.example.sprapp.models.enums.EnumCategoria
data class Servicios(val id : String,
                var nombreServicio : trabajos,
                var descripcionServicio : String,
                var cedulaPrestador: String,
                var categoria: EnumCategoria)