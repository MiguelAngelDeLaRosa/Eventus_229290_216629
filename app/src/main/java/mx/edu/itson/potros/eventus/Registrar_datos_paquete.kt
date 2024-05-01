package mx.edu.itson.potros.eventus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ListAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itson.potros.eventus.adapter.BebidaAdapter
import mx.edu.itson.potros.eventus.adapter.PlatilloAdapter
import mx.edu.itson.potros.eventus.dto.Bebida
import mx.edu.itson.potros.eventus.dto.Evento
import mx.edu.itson.potros.eventus.dto.Paquete
import mx.edu.itson.potros.eventus.dto.Platillo
import mx.edu.itson.potros.eventus.provider.BebidaProvider
import mx.edu.itson.potros.eventus.provider.PlatilloProvider

class Registrar_datos_paquete : AppCompatActivity() {
    var evento: Evento? = null
    lateinit var permiso: CheckBox
    lateinit var platillosElegidos: List<Platillo>
    lateinit var bebidasElegidos: List<Bebida>
    var opcion: String = ""
    var costo: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_datos_paquete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var recibirDatos: Bundle? = intent.extras
        if ( recibirDatos != null) {
            evento = recibirDatos.getSerializable("objEvento") as Evento?
            llenarSpinner()
        }

        val chkPermiso: CheckBox = findViewById(R.id.chkPermisoAlcohol)
        chkPermiso.isClickable = false
        permiso = chkPermiso

        botonAtras()
    }

    private fun botonAtras() {
        val btnAtras = findViewById<ImageButton>(R.id.back_button)

        btnAtras.setOnClickListener(){
            var intent = Intent(this, Registrar_datos_cliente::class.java);
            startActivity(intent)
        }
    }

    private fun llenarSpinner() {
        val spinner_paquetes = findViewById<Spinner>(R.id.spinner_paquetes)

        val optionsPaquetes = listOf("Seleccione", "Paquete simple", "Paquete super fiesta",
                            "Paquete Adultos")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsPaquetes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_paquetes.adapter = adapter

        spinner_paquetes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val opcionSeleccionada = optionsPaquetes[position]
                initRecycerView(opcionSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun initRecycerView(option: String){
        opcion = option
        val recyclerViewPlatillo = findViewById<RecyclerView>(R.id.recyclerViewPlatillos)
        val recyclerViewBebida = findViewById<RecyclerView>(R.id.recyclerViewBebidas)
        recyclerViewPlatillo.layoutManager = LinearLayoutManager(this)
        recyclerViewBebida.layoutManager = LinearLayoutManager(this)

        when (option) {
            "Paquete simple" -> {
                platillosElegidos = PlatilloProvider.platillosListSimple
                bebidasElegidos = BebidaProvider.bebidaListSimple
                permiso.isChecked = false
                costo = 500.0
                recyclerViewPlatillo.adapter = PlatilloAdapter(platillosElegidos)
                recyclerViewBebida.adapter = BebidaAdapter(bebidasElegidos)
            }
            "Paquete super fiesta" -> {
                platillosElegidos = PlatilloProvider.platillosListSuperFiesta
                bebidasElegidos = BebidaProvider.bebidaListSuperFiesta
                permiso.isChecked = false
                costo = 1200.0
                recyclerViewPlatillo.adapter = PlatilloAdapter(platillosElegidos)
                recyclerViewBebida.adapter = BebidaAdapter(bebidasElegidos)
            }
            "Paquete Adultos" -> {
                platillosElegidos = PlatilloProvider.platillosListAdultos
                bebidasElegidos = BebidaProvider.bebidaListAdultos
                permiso.isChecked = true
                costo = 1000.0
                recyclerViewPlatillo.adapter = PlatilloAdapter(platillosElegidos)
                recyclerViewBebida.adapter = BebidaAdapter(bebidasElegidos)
            }
        }
        botonSiguiente()
    }

    private fun botonSiguiente() {
        val btnSiguiente: Button = findViewById(R.id.button_añadirPaquete)

        btnSiguiente.setOnClickListener(){
            if (opcion != "Seleccione" && opcion != "") {
                var paquete = Paquete(opcion, platillosElegidos, bebidasElegidos, permiso.isActivated, costo)
                evento?.paquete = paquete
                //Toast.makeText(this, evento?.paquete.toString(), Toast.LENGTH_LONG).show()
                val enviarDatos = Bundle()
                enviarDatos.putSerializable("objEvento", evento)
                var intent = Intent(this, Cotizacion_evento::class.java);
                intent.putExtras(enviarDatos)
                startActivity(intent);
            }
        }
    }
}