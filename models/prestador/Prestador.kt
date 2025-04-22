package main.models.prestador

import com.google.gson.annotations.SerializedName
import main.models.sercat.EnumCategoria
import java.util.*
data class Prestador(
    @SerializedName("cedula") val cedula: String,
    @SerializedName("nombres") var nombres: String,
    @SerializedName("apellidos") var apellidos: String,
    @SerializedName("celular") var celular: String,
    @SerializedName("direccion") var direccion: String,
    @SerializedName("contrasena") var contrasena: String,
    @SerializedName("titulos_uni") var titulosUni: String,
    @SerializedName("descripcion") var descripcion: String,
    @SerializedName("correo") var correo: String,
    @SerializedName("fecha_nacimiento") var fechaNacimiento: String,
    @SerializedName("foto") var foto: String,
    @SerializedName("id_rol") val idRol: Int = 2,
    @SerializedName("categoria") val categorias: List<String>
)
