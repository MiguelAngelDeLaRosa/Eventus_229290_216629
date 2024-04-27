package mx.edu.itson.potros.eventus.dto

import java.util.Date

data class Evento(var nombreEvento: String,
                  var tipoEvento: String,
                  var paquete: Paquete,
                  var cliente: Cliente,
                  var fechaEvento: Date,
                  var monto: Pago,
                  var horario: Double)
