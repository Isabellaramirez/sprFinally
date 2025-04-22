package com.example.appsrp.models.rol

enum class RolTipo(val idRol: Int) {
    CONTRATISTA(1),
    PRESTADOR(2),
    DESCONOCIDO(-1);

    companion object {
        fun fromId(id: Int?): RolTipo? {
            return values().find { it.idRol == id } ?: DESCONOCIDO
        }
    }
}
