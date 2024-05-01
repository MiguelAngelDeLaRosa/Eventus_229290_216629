package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import mx.edu.itson.potros.eventus.DataClasses.Usuario

class Registro : AppCompatActivity() {

    private val database: DatabaseReference = Firebase.database.reference
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Campos de texto
        val eT_Nombre: EditText = findViewById(R.id.nombre)
        val eT_Apellido: EditText = findViewById(R.id.apellido)
        val eT_Mail: EditText = findViewById(R.id.email)
        val eT_Usuario: EditText = findViewById(R.id.username)
        val eT_Contrasena: EditText = findViewById(R.id.password)

        // Botones
        val bT_Registrar: Button = findViewById(R.id.btnRegistro)
        val bT_Iniciar: Button = findViewById(R.id.btnIniciar)

        bT_Iniciar.setOnClickListener {
            var intent: Intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }

        bT_Registrar.setOnClickListener {
            val nuevoUsuario = Usuario(
                eT_Nombre.text.toString(),
                eT_Apellido.text.toString(),
                eT_Mail.text.toString(),
                eT_Usuario.text.toString(),
                eT_Contrasena.text.toString()
            )

            registrarUsuario(nuevoUsuario)
        }
    }

    private fun registrarUsuario (usuario: Usuario) {
        val nuevoUsuarioId = database.child("Usuario").push().key //Genera ID unico
        var intent: Intent = Intent(this, IniciarSesion::class.java)

        database.child("Usuario").child(nuevoUsuarioId!!).setValue(usuario)
            .addOnSuccessListener {
                registrarUsuarioEnAuth(usuario.email, usuario.password)
                Log.d("Mensaje", "Usuario Registrado correctamente")
                startActivity(intent)
            }
            .addOnFailureListener{
                Log.d("Mensaje", "Hubo un problema al registrar")
            }
    }

    private fun registrarUsuarioEnAuth(correo: String, contrasena: String){
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
}