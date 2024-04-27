package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IniciarSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val button : Button = findViewById(R.id.btnIniciar);

        button.setOnClickListener {
            var intent: Intent = Intent(this, Menu::class.java);
            startActivity(intent);
        }
    }
}