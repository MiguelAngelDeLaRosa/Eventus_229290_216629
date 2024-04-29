package mx.edu.itson.potros.eventus.dto

import java.io.Serializable

data class Cliente(var nombre: String,
                   var telefono: String,
                   var email: String) : Serializable
