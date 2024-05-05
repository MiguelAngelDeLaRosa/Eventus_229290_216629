package mx.edu.itson.potros.eventus

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.itson.potros.eventus.dto.Cliente
import mx.edu.itson.potros.eventus.dto.Evento

class Registrar_datos_cliente : AppCompatActivity() {
    var evento: Evento? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_datos_cliente)

        var recibirDatos: Bundle? = intent.extras

        if ( recibirDatos != null) {
            evento = recibirDatos.getSerializable("objEvento") as Evento?
            botonSiguiente()
        }
    }

    private fun botonSiguiente() {
        val edtNombre: EditText = findViewById(R.id.edit_nombreCliente)
        val edtTelefono: EditText = findViewById(R.id.edit_telefonoCliente)
        val edtEmail: EditText = findViewById(R.id.edit_emailCliente)

        val btnSiguiente: Button = findViewById(R.id.btnContinuar)

        btnSiguiente.setOnClickListener(){
            if (!validarCampos(edtNombre, edtTelefono, edtEmail)){
                var cliente : Cliente = Cliente(edtNombre.text.toString(),
                                        edtTelefono.text.toString(), edtEmail.text.toString())
                evento?.cliente = cliente

                val enviarDatos : Bundle = Bundle()
                enviarDatos.putSerializable("objEvento", evento)
                var intent = Intent(this, Registrar_datos_paquete::class.java);
                intent.putExtras(enviarDatos)
                startActivity(intent)
            }
        }
    }

    private fun validarCampos(edtNombre: EditText, edtTelefono: EditText,
                              edtEmail: EditText): Boolean {
        val nombre = edtNombre.text.toString()
        val telefono = edtTelefono.text.toString()
        val email = edtEmail.text.toString()

        var hayErrores: Boolean = false
        val builder = AlertDialog.Builder(this)

        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()){
            builder.setTitle("Error")
            builder.setMessage("Faltan campos por llenar")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{ dialog, id ->

                })
            hayErrores = true
            builder.show()
        }

        if (!nombre.matches(Regex("[a-zA-Z\\s]+"))){
            builder.setTitle("Error")
            builder.setMessage("El nombre no puede llevar numeros o simbolos")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{ dialog, id ->

                })
            hayErrores = true
            builder.show()
        }

        if(!telefono.matches(Regex("^[0-9]{1,3}[0-9]{3,14}$"))){
            builder.setTitle("Error")
            builder.setMessage("El número de telefono no es valido")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{ dialog, id ->

                })
            hayErrores = true
            builder.show()
        }

        if(!email.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"))){
            builder.setTitle("Error")
            builder.setMessage("El email no es valido")
                .setPositiveButton("ACEPTAR", DialogInterface.OnClickListener{ dialog, id ->

                })
            hayErrores = true
            builder.show()
        }

        return hayErrores
    }
}