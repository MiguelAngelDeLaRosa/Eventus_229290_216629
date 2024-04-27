package mx.edu.itson.potros.eventus.dto

data class Paquete(var nombrePaquete: String,
                   var platillos: List<Platillo>,
                   var bebidas: List<Bebida>,
                   var permisoAlcohol: Boolean,
                   var costo: Double)
