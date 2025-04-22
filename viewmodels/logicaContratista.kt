package main.models.contratista
import com.example.appsrp.api.RetrofitInstance
import com.example.appsrp.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.NoSuchElementException

class LogicaContratista {
/*
    companion object {

        private val apiService = RetrofitInstance.apiService

        fun crearContratista(
            listaContratistas: MutableList<Contratista>,
            cedula: String,
            nombre: String,
            apellido: String,
            direccion: String,
            telefono: String,
            correo: String,
            fechaDeNacimiento: java.util.Date?,
            contrasena: String,
            foto: String,
            idRol: Int = 1
        ): Contratista {
            val contratista = Contratista(
                cedula = cedula,
                nombres = nombre,
                apellidos = apellido,
                direccion = direccion,
                telefono = telefono,
                correo = correo,
                fechaNacimiento = fechaDeNacimiento.toString(), // Formato opcional
                contrasena = contrasena,
                foto = foto,
                idRol = idRol
            )

            listaContratistas.add(contratista)
            return contratista
        }

        fun actualizarContratista(
            listaContratistas: MutableList<Contratista>,
            cedula: String,
            nombre: String? = null,
            apellido: String? = null,
            direccion: String? = null,
            celular: String? = null,
            correo: String? = null,
            fechaDeNacimiento: Date? = null,
            contrasena: String? = null,
            foto: String? = null
        ): Contratista {
            val contratista = listaContratistas.find { it.cedula == cedula }
                ?: throw NoSuchElementException("No se encontró la cédula del contratista")

            nombre?.let { contratista.nombres = it }
            apellido?.let { contratista.apellidos = it }
            direccion?.let { contratista.direccion = it }
            celular?.let { contratista.telefono = it }
            correo?.let { contratista.correo = it }
            fechaDeNacimiento?.let { contratista.fechaNacimiento = it.toString() }
            contrasena?.let { contratista.contrasena = it }
            // No se permite cambiar idRol (suele ser fijo para el rol)
            // foto es inmutable en el modelo, pero si quieres poder cambiarla, cambia el modelo a `var`
            foto?.let {
                val updated = Contratista(
                    cedula = contratista.cedula,
                    nombres = contratista.nombres,
                    apellidos = contratista.apellidos,
                    direccion = contratista.direccion,
                    telefono = contratista.telefono,
                    correo = contratista.correo,
                    fechaNacimiento = contratista.fechaNacimiento,
                    contrasena = contratista.contrasena,
                    foto = it,
                    idRol = contratista.idRol
                )
                listaContratistas.remove(contratista)
                listaContratistas.add(updated)
                return updated
            }

            return contratista
        }

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

        fun crearMapaRegistroContratista(contratista: Contratista): Map<String, Any?> {
            val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fechaFormateada = formatoFecha.format(contratista.fechaNacimiento ?: Date())

            return mapOf(
                "nombres" to contratista.nombres,
                "apellidos" to contratista.apellidos,
                "correo" to contratista.correo,
                "cedula" to contratista.cedula,
                "telefono" to contratista.telefono,
                "direccion" to contratista.direccion,
                "fecha_nacimiento" to fechaFormateada,
                "contrasena" to contratista.contrasena,
                "id_rol" to 1,
                "foto" to contratista.foto
            )
        }

        fun registrarPrestador(contratista: Contratista, onResult: (Boolean) -> Unit) {
                val mapa = crearMapaRegistroContratista(contratista)
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
