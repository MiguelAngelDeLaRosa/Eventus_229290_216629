package mx.edu.itson.potros.eventus.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itson.potros.eventus.R
import mx.edu.itson.potros.eventus.dto.Bebida

class BebidaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val nombreBebida = view.findViewById<TextView>(R.id.txtNombreBebida)
    val tipoBebida = view.findViewById<TextView>(R.id.txtTipoBebida)

    fun render(bebida: Bebida){
        this.nombreBebida.text = bebida.nombreBebida
        this.tipoBebida.text = bebida.tipoBebida
    }
}