package mx.edu.itson.potros.eventus.negocio

import mx.edu.itson.potros.eventus.dto.Anticipo
import mx.edu.itson.potros.eventus.dto.Cliente
import mx.edu.itson.potros.eventus.dto.Evento
import java.io.Serializable

class ControlAnticipo(var evento: Evento,
                      var cliente: Cliente,
                      var anticipo: List<Anticipo>?,
                      var estaLiberado: Boolean): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ControlAnticipo

        return evento == other.evento
    }

    override fun hashCode(): Int {
        return evento.hashCode()
    }

    override fun toString(): String {
        return "ControlAnticipo(evento=$evento, cliente=$cliente, anticipo=$anticipo, estaLiberado=$estaLiberado)"
    }


}