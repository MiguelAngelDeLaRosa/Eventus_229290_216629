package mx.edu.itson.potros.eventus

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import mx.edu.itson.potros.eventus.dto.Evento

class Seleccion_evento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccion_evento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        llenarSpiner()
        salir()
    }

    private fun salir() {
        val btnAtras = findViewById<ImageButton>(R.id.backButton)

        btnAtras.setOnClickListener(){
            val intent = Intent(this, Menu::class.java);
            startActivity(intent)
        }
    }

    private fun llenarSpiner() {
        val spinnerEventos = findViewById<Spinner>(R.id.lista_eventos)
        val dbRef = FirebaseDatabase.getInstance().getReference(Evento::class.java.simpleName)

        val listEventos = ArrayList<Evento>()
        val listAdapter = ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_1, listEventos)
        spinnerEventos.adapter = listAdapter

        dbRef.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val event = snapshot.getValue(Evento::class.java)
                val exception = ""
                Log.e(TAG, "Error al registrar control: $exception")
                if (event != null) {
                    listEventos.add(event)
                    listAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                listAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        spinnerEventos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedEvent = listEventos[position]
                mostrarDatos(selectedEvent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun mostrarDatos(evento: Evento) {
        val txtCostoHora = findViewById<TextView>(R.id.txt_costo_hora)
        val txtCostoPaquete = findViewById<TextView>(R.id.txt_costo_paquete)
        val txtMonto = findViewById<TextView>(R.id.txt_monto_total)

        txtCostoHora.text = "Costo por hora: $"+evento.monto.costoPorHora.toString()
        txtCostoPaquete.text = "Costo del paquete: $"+evento.monto.costoPaquete.toString()
        txtMonto.text = "Monto total: $"+evento.monto.montoTotal.toString()
    }
}