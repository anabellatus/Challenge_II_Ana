package com.nanana.covidnow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.R
import com.nanana.covidnow.data.HotlineModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.hotline_item.*

class HotlineAdapter ( private val context: Context, private var list: List<HotlineModel>) : RecyclerView.Adapter<HotlineAdapter.ViewHolder>() {

    var listener: dataListener? = null
    private var auth: FirebaseAuth? = null
    lateinit var ref: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.hotline_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: HotlineModel) {
            nama.text = item.nama
            nomor.text = item.nomor.toString()
        }
    }

    fun setData(list: List<HotlineModel>){
        this.list = list
        notifyDataSetChanged()
    }

    interface dataListener {
        fun onDeleteData(data: HotlineModel, position: Int)
    }
}