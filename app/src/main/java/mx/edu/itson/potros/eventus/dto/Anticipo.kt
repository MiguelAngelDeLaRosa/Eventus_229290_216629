package mx.edu.itson.potros.eventus.dto

data class Anticipo(
    var evento: Evento?,
    var pagoAnticipo: Double,
    var tipoPago: String?
)