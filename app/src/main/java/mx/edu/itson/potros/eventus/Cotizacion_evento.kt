package mx.edu.itson.potros.eventus

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.itson.potros.eventus.dto.Evento

class Cotizacion_evento : AppCompatActivity() {
    var evento: Evento? = null
    var costoPorHora: Double = 0.0
    var montoToal: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion_evento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var recibirDatos: Bundle? = intent.extras
        if ( recibirDatos != null) {
            evento = recibirDatos.getSerializable("objEvento") as Evento?
            Toast.makeText(this, evento?.horario.toString(), Toast.LENGTH_LONG).show()
            llenarDatos()
        }

    }

    private fun llenarDatos() {
        val txtCostoHora: TextView = findViewById(R.id.txt_costo_hora)
        val txtCostoPaquete: TextView = findViewById(R.id.txt_costo_paquete)
        val txtMonto: TextView = findViewById(R.id.txt_monto_total)

        val hInicio: String = evento?.horario?.horaInicio ?: "1:00"
        val hFinal: String = evento?.horario?.horaFinal ?: "6:00"
        val costoPaquete = evento?.paquete?.costo

        var costoXhora = calculoCostoPorHora(hInicio, hFinal)

        txtCostoPaquete.text = costoPaquete.toString()
        val monto = costoPaquete?.plus(1000.0)
        txtMonto.text = monto.toString()
        txtCostoHora.text = costoXhora.toString()
    }

    private fun calculoCostoPorHora(horaInicio: String, horaFinal: String): Double {
        var inicio = parsearHoraADouble(horaInicio)
        var final = parsearHoraADouble(horaFinal)
        var costo: Double = (final - inicio) * 100.0
        return costo
    }

    private fun parsearHoraADouble(horaString: String): Double {
        val partes = horaString.split(":") // Divide el string en partes usando ":" como separador
        val horas = partes[0].toInt() // Obtiene las horas como entero
        val minutos = partes[1].toInt() // Obtiene los minutos como entero
        val horaTotal = horas + (minutos / 60.0) // Calcula la hora total como horas decimales
        return horaTotal
    }
}