package mx.edu.itson.potros.eventus

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import mx.edu.itson.potros.eventus.dto.Evento
import mx.edu.itson.potros.eventus.dto.Horario
import java.text.SimpleDateFormat
import java.util.Date

class Registrar_datos_evento : AppCompatActivity() {
    lateinit var fecha: EditText
    lateinit var horaInicio: EditText
    lateinit var horaFin: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_datos_evento)

        var tipoEventoText : TextView = findViewById(R.id.titulo_evento)

        var recibirDatos : Bundle? = intent.extras
        var tipoEvento : String = ""

        fecha = findViewById(R.id.edit_fechaEvento)
        horaInicio = findViewById(R.id.ed_horaInicio)
        horaFin = findViewById(R.id.ed_horaFinal)

        if (recibirDatos != null) {
            tipoEvento = recibirDatos.getString("tipoEvento").toString()
            tipoEventoText.text = tipoEvento
            botonContinuar(tipoEvento)
        }

        fecha.setOnClickListener() { showDateDialog() }
        horaInicio.setOnClickListener() { showInitTimeDialog() }
        horaFin.setOnClickListener(){ showFinalTimeDialog() }

        botonAtras()
    }

    private fun showFinalTimeDialog() {
        val timePicker = InitTimePickerFragment{onTimeSelected2(it)}
        timePicker.show(supportFragmentManager, "Time")
    }

    private fun showInitTimeDialog() {
        val timePicker = InitTimePickerFragment{onTimeSelected(it)}
        timePicker.show(supportFragmentManager, "Time")
    }

    private fun onTimeSelected(time: String){
        horaInicio.setText(time)
    }

    private fun onTimeSelected2(time: String){
        horaFin.setText(time)
    }

    private fun showDateDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day: Int, month: Int, year: Int){
        fecha.setText("$day/$month/$year")
    }

    private fun botonAtras() {
        val btnAtras = findViewById<ImageButton>(R.id.back_button)

        btnAtras.setOnClickListener(){
            var intent = Intent(this, SelectTipoEvento::class.java);
            startActivity(intent)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun botonContinuar(tipoEvento: String) {
        val btnSiguiente: Button = findViewById(R.id.btnContinuar)
        val txtNombreEvento = findViewById<EditText>(R.id.edit_nombreEvento)
        // Intentar crear las variables de los campos aqui como en validarCampos
        btnSiguiente.setOnClickListener(){
            if (!validarCampos()){
                var horario: Horario = Horario(horaInicio.text.toString(), horaFin.text.toString())
                var nombreEvento = txtNombreEvento.text.toString()

                val fechaEvento: String = fecha.text.toString()
                var evento: Evento = Evento(nombreEvento, tipoEvento, fechaEvento, horario)

                val enviarDatos : Bundle = Bundle()
                enviarDatos.putSerializable("objEvento", evento)
                var intent = Intent(this, Registrar_datos_cliente::class.java);
                intent.putExtras(enviarDatos)
                startActivity(intent);
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
            builder.show()
        }

        if (!txtNombreEvento.text.toString().matches(Regex("[a-zA-Z\\s]+"))){
            builder.setTitle("Error")
            builder.setMessage("El nombre del evento no puede llevar numeros")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{dialog, id ->

                })
            hayErrores = true
            builder.show()
        }

        return hayErrores
    }
}