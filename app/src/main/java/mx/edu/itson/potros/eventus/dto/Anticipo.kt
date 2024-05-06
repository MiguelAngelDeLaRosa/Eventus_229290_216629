package mx.edu.itson.potros.eventus.dto

import java.io.Serializable

data class Anticipo(val pagoAnticipo: Double,
                    val fechaAnticipo: String,
                    val tipoPago: String): Serializable {

                        constructor(): this(0.0, "", "")
                    }
