package mx.edu.itson.potros.eventus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.itson.potros.eventus.dto.Evento

class Evento_registro : AppCompatActivity() {
    var evento: Evento? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_evento_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var recibirDatos: Bundle? = intent.extras
        if ( recibirDatos != null) {
            evento = recibirDatos.getSerializable("objEvento") as Evento?
            mostrarDatos()
        }
        regresar()
    }

    private fun regresar() {
        val btnRegresar = findViewById<Button>(R.id.btn_regresar)

        btnRegresar.setOnClickListener(){
            val intent = Intent(this, Menu::class.java);
            startActivity(intent);
        }
    }

    @SuppressLint("SetTextI18n")
    private fun mostrarDatos() {
        val nombreEvento = findViewById<TextView>(R.id.nombre_evento)
        val monto = findViewById<TextView>(R.id.monto_evento)

        nombreEvento.text = evento?.nombreEvento
        monto.text = "Monto: $"+evento?.monto?.montoTotal.toString()
    }
}