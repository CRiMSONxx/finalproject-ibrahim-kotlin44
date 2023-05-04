package com.example.ibrahim_networking_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterCat(val dataCat: List<ResultsItem?>?) : RecyclerView.Adapter<AdapterCat.ListViewHolder>() {

    class ListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthor: TextView = view.findViewById<TextView>(R.id.tv_breed_fill)
        val tvContent: TextView = view.findViewById<TextView>(R.id.tv_country_fill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_cat, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.apply {
            tvAuthor.text = dataCat?.get(position)?.author
            tvContent.text = dataCat?.get(position)?.content
            //tvDateAdd.text = dataCat?.get(position)?.dateAdded
            //tvSlugAuthor.text = dataCat?.get(position)?.authorSlug
            //tvID.text = dataCat?.get(position)?.id

            itemView.setOnClickListener {
                val kata_bijak = dataCat?.get(position)?.content
                val penulis = dataCat?.get(position)?.author
                Toast.makeText(holder.itemView.context, "'${kata_bijak}' - ${penulis}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        if (dataCat != null) {
            return dataCat.size
        }
        return 0
    }

}