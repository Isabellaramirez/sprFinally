package main.models.contratista

import java.time.LocalDate

data class Contratista(var cedula: String,
                  var nombre: String,
                  var apellido: String,
                  var ciudad: String,
                  var celular: String,
                  var correo: String,
                  var fechaDeNacimiento: LocalDate){}

