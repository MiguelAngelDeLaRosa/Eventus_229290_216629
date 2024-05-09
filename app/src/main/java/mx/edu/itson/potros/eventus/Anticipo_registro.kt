package mx.edu.itson.potros.eventus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Anticipo_registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anticipo_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val eventoTxt: TextView = findViewById(R.id.txt_Evento)
        val pagoTxt: TextView = findViewById(R.id.txt_Pago)
        val tipoPagoTxt: TextView = findViewById(R.id.txt_tipoPago)
        val volverButton: Button = findViewById(R.id.btn_volver)

        eventoTxt.text = intent.getStringExtra("anticipo_evento_nombre")
        pagoTxt.text = intent.getStringExtra("anticipo_evento_monto")
        tipoPagoTxt.text = intent.getStringExtra("anticipo_evento_tipoPago")

        volverButton.setOnClickListener {
            val intent: Intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }
}