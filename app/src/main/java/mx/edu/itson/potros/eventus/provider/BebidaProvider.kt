package mx.edu.itson.potros.eventus.provider

import mx.edu.itson.potros.eventus.dto.Bebida

class BebidaProvider {
    companion object{
        val bebidaListSimple = listOf<Bebida>(
            Bebida("Refresco de cola", "Refresco"),
            Bebida("Sprite", "Refresco")
        )

        val bebidaListSuperFiesta = listOf<Bebida>(
            Bebida("Jamaica", "Agua fresca"),
            Bebida("Refresco de cola", "Refresco"),
            Bebida("Agua de piña", "Agua fresca"),
            Bebida("Horchata", "Agua fresca")
        )

        val bebidaListAdultos = listOf<Bebida>(
            Bebida("Corona", "Alcohol"),
            Bebida("Tequila", "Alcohol"),
            Bebida("Tejuino", "Alcohol"),
            Bebida("Cebada", "Agua fresca")
        )
    }
}