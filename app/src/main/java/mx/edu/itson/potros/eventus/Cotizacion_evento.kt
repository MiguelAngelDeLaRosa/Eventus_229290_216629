package mx.edu.itson.potros.eventus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.edu.itson.potros.eventus.dto.Evento
import mx.edu.itson.potros.eventus.dto.Pago
import mx.edu.itson.potros.eventus.negocio.ControlAnticipo

class Cotizacion_evento : AppCompatActivity() {
    var evento: Evento? = null
    var costoPorHora: Double = 0.0
    var montoToal: Double = 0.0
    var costoPaquete: Double = 0.0

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
            llenarDatos()
            botonRegistrar()
        }
        botonBack()
    }

    private fun botonRegistrar() {
        val btnRegistrar = findViewById<Button>(R.id.btn_registrar_evento)

        btnRegistrar.setOnClickListener(){
            registrarEvento()
        }
    }

    private fun registrarEvento() {
        val fb = FirebaseDatabase.getInstance().getReference("Evento")
        fb.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val pago = Pago(costoPorHora, costoPaquete, montoToal)
                evento?.monto = pago
                fb.push().setValue(evento)
                asignarControlAnticipos(evento)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun asignarControlAnticipos(evento: Evento?){
        val control = evento?.let { ControlAnticipo(evento, it.cliente, null, false) }
        if (control != null) {
            registrarControl(control)
        }
    }

    private fun registrarControl(controlAnticipo: ControlAnticipo){
        val fb = FirebaseDatabase.getInstance().getReference("ControlAnticipos")
        fb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fb.push().setValue(controlAnticipo)
                siguiente()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun siguiente() {
        Toast.makeText(this, "Se registro el evento", Toast.LENGTH_LONG).show()
        val enviarDatos = Bundle()
        enviarDatos.putSerializable("objEvento", evento)
        var intent = Intent(this, Evento_registro::class.java);
        intent.putExtras(enviarDatos)
        startActivity(intent);
    }

    private fun botonBack() {
        val btnAtras = findViewById<ImageButton>(R.id.back_button)

        btnAtras.setOnClickListener(){
            val enviarDatos : Bundle = Bundle()
            enviarDatos.putSerializable("objEvento", evento)
            var intent = Intent(this, Registrar_datos_paquete::class.java);
            intent.putExtras(enviarDatos)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun llenarDatos() {
        val txtCostoHora: TextView = findViewById(R.id.txt_costo_hora)
        val txtCostoPaquete: TextView = findViewById(R.id.txt_costo_paquete)
        val txtMonto: TextView = findViewById(R.id.txt_monto_total)

        val hInicio: String = evento?.horario?.horaInicio ?: "1:00"
        val hFinal: String = evento?.horario?.horaFinal ?: "6:00"
        costoPaquete = evento?.paquete?.costo!!

        costoPorHora = calculoCostoPorHora(hInicio, hFinal)

        montoToal = costoPaquete.plus(costoPorHora)
        txtMonto.text = "Monto total: $$montoToal"
        txtCostoHora.text = "Costo por hora: $$costoPorHora"
        txtCostoPaquete.text = "Costo del paquete: $$costoPaquete"
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