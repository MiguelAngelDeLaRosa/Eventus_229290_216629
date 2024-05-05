package mx.edu.itson.potros.eventus.dto

import java.io.Serializable

data class Cliente(
    val nombre: String,
    val telefono: String,
    val email: String
) : Serializable {

    constructor() : this("", "", "")
}
