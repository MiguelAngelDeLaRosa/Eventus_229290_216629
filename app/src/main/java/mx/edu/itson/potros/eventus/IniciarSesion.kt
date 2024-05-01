package mx.edu.itson.potros.eventus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class IniciarSesion : AppCompatActivity() {
    private val database: DatabaseReference = Firebase.database.reference
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        //Campos de texto
        val eT_username: EditText = findViewById(R.id.username)
        val eT_password: EditText = findViewById(R.id.password)

        // Botones
        val buttonIniciarSesion : Button = findViewById(R.id.btnIniciar)
        val buttonRegistrar : Button = findViewById(R.id.btnRegistro)

        buttonRegistrar.setOnClickListener {
            var intent: Intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            val nombreUsuario = eT_username.text.toString()
            val contrasena = eT_password.text.toString()
            iniciarSesion(nombreUsuario, contrasena)
        }

    }
    // Metodo para llevar a cabo el inicio de sesion
    private fun iniciarSesion(username: String, password: String){
        var intent: Intent = Intent(this, Menu::class.java);
        val dbref = database.child("Usuario")
        dbref.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        for (usuarioSnapshot in snapshot.children) {
                            val eMail = usuarioSnapshot.child("email").getValue(String::class.java)
                            if(eMail != null){
                                auth.signInWithEmailAndPassword(eMail, password)
                                    .addOnSuccessListener {
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener {
                                        // Manejar Fallo en el inicio de sesion
                                    }
                            } else {
                                // No se encontro el correo electronico asociado al nombre del usuario
                            }
                        }
                    } else {
                        // No se encontro el nombre de usuario en la base de datos
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}