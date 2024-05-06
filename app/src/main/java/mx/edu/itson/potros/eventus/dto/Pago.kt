package mx.edu.itson.potros.eventus.dto

import java.io.Serializable

data class Pago(var costoPorHora: Double,
                var costoPaquete: Double,
                var montoTotal: Double) : Serializable {

                    constructor(): this(0.0, 0.0, 0.0)
                }
