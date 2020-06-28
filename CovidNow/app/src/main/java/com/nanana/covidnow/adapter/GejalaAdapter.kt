package com.nanana.covidnow.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.R
import com.nanana.covidnow.data.GejalaModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gejala_item.*

class GejalaAdapter(private val context: Context, private var list: List<GejalaModel>) : RecyclerView.Adapter<GejalaAdapter.ViewHolder>()  {

    var listener: dataListener? = null
    private var auth: FirebaseAuth? = null
    lateinit var ref: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.gejala_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: GejalaModel) {
            gejala.text = item.gejala
        }
    }

    fun setData(list: List<GejalaModel>){
        this.list = list
        notifyDataSetChanged()
    }

    interface dataListener {
        fun onDeleteData(data: GejalaModel, position: Int)
    }
}