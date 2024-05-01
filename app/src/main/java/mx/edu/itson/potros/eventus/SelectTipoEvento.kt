package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectTipoEvento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tipo_evento)

        val btnCumple : Button = findViewById(R.id.btnCumple);
        val btnFinDeAno : Button = findViewById(R.id.btnFindeano);
        val btnBoda : Button = findViewById(R.id.btnBoda);
        val btnShow : Button = findViewById(R.id.btnShow);
        val btnSoltero : Button = findViewById(R.id.btnSoltero);
        val btnSoltera : Button = findViewById(R.id.btnSoltera);
        val btnGraduacion : Button = findViewById(R.id.btnGraduacion);
        val btnBabyshower : Button = findViewById(R.id.btnBabyshower);

        val btnRegresar : Button = findViewById(R.id.btnRegresar)

        val enviarTipo : Bundle = Bundle()
        var intent: Intent

        btnCumple.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnCumple.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnFinDeAno.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnFinDeAno.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnBoda.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnBoda.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnShow.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnShow.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnSoltero.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnSoltero.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnSoltera.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnSoltera.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnGraduacion.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnGraduacion.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnBabyshower.setOnClickListener(){
            enviarTipo.putString("tipoEvento", btnBabyshower.text.toString())
            intent = Intent(this, Registrar_datos_evento::class.java);
            intent.putExtras(enviarTipo)
            startActivity(intent);
        }

        btnRegresar.setOnClickListener(){
            intent = Intent(this, Menu::class.java);
            startActivity(intent);
        }
    }
}