package mx.edu.itson.potros.eventus

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import mx.edu.itson.potros.eventus.dto.Evento
import mx.edu.itson.potros.eventus.dto.Horario
import java.text.SimpleDateFormat
import java.util.Date

class Registrar_datos_evento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_datos_evento)

        var tipoEventoText : TextView = findViewById(R.id.titulo_evento)

        var recibirDatos : Bundle? = intent.extras
        var tipoEvento : String = ""

        if (recibirDatos != null) {
            tipoEvento = recibirDatos.getString("tipoEvento").toString()
            tipoEventoText.text = tipoEvento
        }

        botonContinuar()
    }

    private fun botonContinuar() {
        val btnSiguiente: Button = findViewById(R.id.btnContinuar)
        // Intentar crear las variables de los campos aqui como en validarCampos
        btnSiguiente.setOnClickListener(){
            if (!validarCampos()){
                //var horario: Horario = Horario(edtInicio.text.toString(), edtFinal.text.toString())
                //var nombreEvento = txtNombreEvento.text.toString()

                //val fechaString = edtFecha.text.toString()
                //val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                //val fechaEvento: Date = dateFormat.parse(fechaString)
                //var evento: Evento = Evento(nombreEvento, tipoEvento, fechaEvento, horario)

                //val enviarTipo : Bundle = Bundle()
                //enviarTipo.putString("objEvento", evento.toString())
                //var intent = Intent(this, Registrar_datos_cliente::class.java);
                //intent.putExtra("objEvento", evento)
                //startActivity(intent);
            }
        }
    }

    private fun validarCampos(): Boolean {
        val txtNombreEvento = findViewById<EditText>(R.id.edit_nombreEvento)
        val edtFecha = findViewById<EditText>(R.id.edit_fechaEvento)
        val edtInicio = findViewById<EditText>(R.id.ed_horaInicio)
        val edtFinal = findViewById<EditText>(R.id.ed_horaFinal)
        var hayErrores: Boolean = false
        val builder = AlertDialog.Builder(this)

        if(txtNombreEvento.text.toString().isEmpty()
                    || edtFecha.text.toString().isEmpty()
                    || edtInicio.text.toString().isEmpty()
                    || edtFinal.text.toString().isEmpty()){
            builder.setTitle("Error")
            builder.setMessage("Faltan campos por llenar")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{dialog, id ->

                })
            hayErrores = true
        }

        if (!txtNombreEvento.text.toString().matches(Regex("[^0-9]+"))){
            builder.setTitle("Error")
            builder.setMessage("El nombre del evento no puede llevar numeros")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{dialog, id ->

                })
            hayErrores = true
        }
        builder.show()
        return hayErrores
    }
}