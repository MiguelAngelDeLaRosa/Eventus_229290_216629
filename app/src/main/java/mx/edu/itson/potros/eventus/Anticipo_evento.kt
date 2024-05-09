package mx.edu.itson.potros.eventus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.edu.itson.potros.eventus.dto.Anticipo
import mx.edu.itson.potros.eventus.dto.Evento
import mx.edu.itson.potros.eventus.negocio.ControlAnticipo

class Anticipo_evento : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var adapter: ArrayAdapter<Evento>
    private lateinit var boxEvento: Spinner
    private lateinit var boxPagos: Spinner
    private lateinit var eTPago: EditText
    private lateinit var txtMonto: TextView
    private lateinit var buttonPago: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anticipo_evento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbref = FirebaseDatabase.getInstance().getReference("Evento")

        boxEvento = findViewById(R.id.box_Evento)
        boxPagos = findViewById(R.id.box_Pago)
        eTPago = findViewById(R.id.eT_Pago)
        txtMonto = findViewById(R.id.txt_Monto)
        buttonPago = findViewById(R.id.btnPagoAnticipo)

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        boxEvento.adapter = adapter

        cargarDatosSpinner()



        boxEvento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val seleccion = parent?.getItemAtPosition(position) as Evento
                txtMonto.text = "$ "+seleccion.monto.montoTotal.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        buttonPago.setOnClickListener {
            registrarAnticipo()
        }
    }

    private fun cargarDatosSpinner(){
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                adapter.clear()
                for (snapshot in dataSnapshot.children) {
                    val evento = snapshot.getValue(Evento::class.java)
                    evento?.let { adapter.add(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Anticipo_evento, "Ocurrio un error al cargar los datos de los eventos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun registrarAnticipo(){
        val eventoSeleccionado = boxEvento.selectedItem as Evento

        val pagoAnticipo = eTPago.text.toString().toDoubleOrNull()
        val tipoPago = boxPagos.selectedItem as String
        val dbAnticipo: DatabaseReference = FirebaseDatabase.getInstance().getReference("Anticipos")
        val intent: Intent = Intent(this, Anticipo_registro::class.java)

        if(eventoSeleccionado != null && pagoAnticipo != null && tipoPago.isNotEmpty()) {
            val nuevoAnticipo = Anticipo(eventoSeleccionado, pagoAnticipo, tipoPago)
            val nuevoMontoTotal = eventoSeleccionado.monto.montoTotal - pagoAnticipo
            eventoSeleccionado.monto.montoTotal = nuevoMontoTotal
            dbAnticipo.push().setValue(nuevoAnticipo)
                .addOnSuccessListener {
                    dbref.child(eventoSeleccionado.nombreEvento).setValue(eventoSeleccionado)
                    Toast.makeText(this, "Anticipo Registrado", Toast.LENGTH_SHORT).show()
                    intent.putExtra("anticipo_evento_nombre", eventoSeleccionado.nombreEvento.toString())
                    intent.putExtra("anticipo_evento_monto", "$ "+pagoAnticipo.toString())
                    intent.putExtra("anticipo_evento_tipoPago", tipoPago)
                    startActivity(intent)
                }
        } else {
            Toast.makeText(this, "Llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

}