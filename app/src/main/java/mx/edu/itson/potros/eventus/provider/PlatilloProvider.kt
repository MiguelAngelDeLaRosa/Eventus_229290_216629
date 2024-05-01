package mx.edu.itson.potros.eventus.provider

import mx.edu.itson.potros.eventus.dto.Platillo

class PlatilloProvider {
    companion object{
        val platillosListSimple = listOf<Platillo>(
            Platillo("Ensalada", "entrada"),
            Platillo("Pure de papa con carne de cerdo", "platillo fuerte"),
            Platillo("Bollitos", "postre")
        )

        val platillosListSuperFiesta = listOf<Platillo>(
            Platillo("Gelatinas", "entrada"),
            Platillo("Pizza de pepperoni y de carne", "platillo fuerte"),
            Platillo("Hamburguesas", "platillo fuerte"),
            Platillo("Paletas de hielo", "postre")
        )

        val platillosListAdultos = listOf<Platillo>(
            Platillo("Cacahuates", "entrada"),
            Platillo("Tacos al pastor", "platillo fuerte"),
            Platillo("Flan", "postre")
        )
    }
}