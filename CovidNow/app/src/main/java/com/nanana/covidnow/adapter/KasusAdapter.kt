package com.nanana.covidnow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nanana.covidnow.R
import com.nanana.covidnow.data.Data
import com.nanana.covidnow.data.DataKasus
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.kasus_item.*

class KasusAdapter(private val context: Context, private val items: List<Data>, private val listener: (Data) -> Unit
) : RecyclerView.Adapter<KasusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        context, LayoutInflater.from(context).inflate(R.layout.kasus_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Data, listener: (Data)-> Unit){
            if (item.jenisKelamin == 0){
                jk.setImageResource(R.drawable.ic_woman)
            }else{
                jk.setImageResource(R.drawable.ic_man)
            }

            kode_pas.text = "Kode Pasien : "+ item.kodePasien.toString()
            prov.text = "Provinsi : "+ item.provinsi.toString()
            umur.text = "Umur : "+ item.umur.toString()
            if (item.wn == 1){
                kewarganegaraan.text ="Kewarganegaraan : "+ context.getString(R.string.wni)
            }else{
                kewarganegaraan.text ="Kewarganegaraan : "+ item.detailWn.toString()
            }

//            keterangan.text ="Keterangan : "+ item.keterangan.toString()

            containerView.setOnClickListener{
                listener(item)
            }
        }

    }
}