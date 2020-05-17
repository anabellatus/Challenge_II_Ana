package com.nanana.covidnow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nanana.covidnow.R
import com.nanana.covidnow.data.Attributes
import com.nanana.covidnow.data.DataProvinceItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.province_item.*

class ProvinceAdapter(
    private val context: Context,
    private val items: List<DataProvinceItem>,
    private val listener: (DataProvinceItem) -> Unit
) : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        context, LayoutInflater.from(context).inflate(R.layout.province_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: DataProvinceItem, listener: (DataProvinceItem)-> Unit){
            name.text =      "Provinsi    : "+ item.attributes.provinsi
            positif.text =   "Positif     : "+item.attributes.kasusPosi.toString()
            meninggal.text = "Meninggal   : "+item.attributes.kasusMeni.toString()
            sembuh.text =    "Sembuh      : "+item.attributes.kasusSemb.toString()

            containerView.setOnClickListener{
                listener(item)
            }
        }

    }
}