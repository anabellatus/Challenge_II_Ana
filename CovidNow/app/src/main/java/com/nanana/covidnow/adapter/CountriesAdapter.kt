package com.nanana.covidnow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nanana.covidnow.R
import com.nanana.covidnow.data.DataCountriesItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.indo_item.*

class CountriesAdapter(
    private val context: Context,
    private val items: List<DataCountriesItem>,
    private val listener: (DataCountriesItem) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>()  {

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
        fun bindItem(item: DataCountriesItem, listener: (DataCountriesItem)-> Unit){

            name.text = item.country
            positif.text =   "Positif     : " +item.cases.toString()
            sembuh.text =    "Sembuh      : " +item.recovered.toString()
            meninggal.text = "Meninggal   : " +item.deaths.toString()
            dirawat.text =   "Dirawat     : " +item.active.toString()

            Glide.with(context)
                .load(item.countryInfo.flag)
                .into(flag)

            containerView.setOnClickListener{
                listener(item)
            }
        }

    }

}