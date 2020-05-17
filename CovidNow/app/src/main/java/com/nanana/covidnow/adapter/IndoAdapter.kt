package com.nanana.covidnow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nanana.covidnow.R
import com.nanana.covidnow.data.DataIndoItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.indo_item.*

class IndoAdapter( private val context: Context, private val items: List<DataIndoItem>, private val listener: (DataIndoItem) -> Unit
) : RecyclerView.Adapter<IndoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        context, LayoutInflater.from(context).inflate(R.layout.indo_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: DataIndoItem, listener: (DataIndoItem)-> Unit){
            name.text = item.name
            positif.text =   "Positif \t: "+item.positif
            meninggal.text = "Meninggal \t: "+item.meninggal
            sembuh.text =    "Sembuh \t: "+item.sembuh
            dirawat.text =   "Dirawat \t: "+item.dirawat

            containerView.setOnClickListener{
                listener(item)
            }
        }
    }
}