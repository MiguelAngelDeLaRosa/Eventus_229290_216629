package mx.edu.itson.potros.eventus.dto

import java.io.Serializable

data class Paquete(
    var nombrePaquete: String,
    var platillos: List<Platillo>?,
    var bebidas: List<Bebida>?,
    var permisoAlcohol: Boolean,
    var costo: Double) : Serializable {

                       constructor(): this("", null, null,
                           false, 0.0)
                   }
