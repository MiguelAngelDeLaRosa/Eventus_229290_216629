package mx.edu.itson.potros.eventus

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.itson.potros.eventus.dto.Evento

class Registrar_datos_paquete : AppCompatActivity() {
    var evento: Evento? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_datos_paquete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var recibirDatos: Bundle? = intent.extras
        if ( recibirDatos != null) {
            evento = recibirDatos.getSerializable("objEvento") as Evento?
            Toast.makeText(this, evento.toString(), Toast.LENGTH_LONG).show()
            //botonSiguiente()
        }
    }

    private fun botonSiguiente() {
        TODO("Not yet implemented")
    }
}