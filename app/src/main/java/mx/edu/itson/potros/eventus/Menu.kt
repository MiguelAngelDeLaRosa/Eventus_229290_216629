package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val button : Button = findViewById(R.id.btnSalir)
        val btnRegistro: Button = findViewById(R.id.btnRegistro)
        val btnCotizacion: Button = findViewById(R.id.btnCotizaciones)

        var intent: Intent

        button.setOnClickListener(){
            intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

        btnRegistro.setOnClickListener(){
            intent = Intent(this, SelectTipoEvento::class.java);
            startActivity(intent);
        }

        btnCotizacion.setOnClickListener(){
            intent = Intent(this, Seleccion_evento::class.java)
            startActivity(intent)
        }
    }
}