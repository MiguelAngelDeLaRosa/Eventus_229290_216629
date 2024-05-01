package mx.edu.itson.potros.eventus.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itson.potros.eventus.R
import mx.edu.itson.potros.eventus.dto.Platillo

class PlatilloViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val nombrePlatillo = view.findViewById<TextView>(R.id.txtNombrePlatillo)
    val tipoPlatillo = view.findViewById<TextView>(R.id.txtTipoPlatillo)
    fun render(platillo: Platillo){
        this.nombrePlatillo.text = platillo.nombrePlatillo
        this.tipoPlatillo.text = platillo.tipoPlatillo
    }
}