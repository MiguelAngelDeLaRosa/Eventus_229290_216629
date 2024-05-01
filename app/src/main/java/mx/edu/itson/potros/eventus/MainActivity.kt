package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonIniciarSesion : Button = findViewById(R.id.btnIniciar)
        val buttonRegistrar: Button = findViewById(R.id.btnRegistro)

        buttonIniciarSesion.setOnClickListener {
            var intent: Intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }

        buttonRegistrar.setOnClickListener {
            var intent: Intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}