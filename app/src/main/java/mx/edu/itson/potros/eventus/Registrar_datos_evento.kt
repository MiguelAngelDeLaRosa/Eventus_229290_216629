package mx.edu.itson.potros.eventus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Registrar_datos_evento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_datos_evento)

        var tipoEventoText : TextView = findViewById(R.id.titulo_evento)

        var recibirDatos : Bundle? = intent.extras
        var tipoEvento : String

        if (recibirDatos != null) {
            tipoEvento = recibirDatos.getString("tipoEvento").toString()
            tipoEventoText.text = tipoEvento
        }

    }
}