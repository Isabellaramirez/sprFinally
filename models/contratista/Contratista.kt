package main.models.contratista

import com.google.gson.annotations.SerializedName
import java.util.*
data class Contratista(
    @SerializedName("cedula") val cedula: String,
    @SerializedName("nombres") var nombres: String,
    @SerializedName("apellidos") var apellidos: String,
    @SerializedName("celular") var telefono: String,
    @SerializedName("direccion") var direccion: String,
    @SerializedName("correo") var correo: String,
    @SerializedName("fecha_nacimiento") var fechaNacimiento: String,
    @SerializedName("contraseña") var contrasena: String,
    @SerializedName("foto") val foto: String,
    @SerializedName("id_rol") val idRol: Int = 1 // 1 es contratista
)
