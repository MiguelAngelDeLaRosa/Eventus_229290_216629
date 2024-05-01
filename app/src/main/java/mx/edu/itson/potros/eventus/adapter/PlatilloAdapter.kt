package mx.edu.itson.potros.eventus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itson.potros.eventus.R
import mx.edu.itson.potros.eventus.dto.Platillo

class PlatilloAdapter(private val platilloList:List<Platillo>) : RecyclerView.Adapter<PlatilloViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlatilloViewHolder(layoutInflater.inflate(R.layout.item_platillo, parent, false))
    }

    override fun getItemCount(): Int = platilloList.size

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val item = platilloList[position]
        holder.render(item)
    }

}