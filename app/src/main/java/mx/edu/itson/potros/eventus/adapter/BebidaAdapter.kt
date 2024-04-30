package mx.edu.itson.potros.eventus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itson.potros.eventus.R
import mx.edu.itson.potros.eventus.dto.Bebida

class BebidaAdapter(private val bebidaList: List<Bebida>): RecyclerView.Adapter<BebidaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BebidaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BebidaViewHolder(layoutInflater.inflate(R.layout.item_bebida, parent, false))
    }

    override fun getItemCount(): Int = bebidaList.size

    override fun onBindViewHolder(holder: BebidaViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}