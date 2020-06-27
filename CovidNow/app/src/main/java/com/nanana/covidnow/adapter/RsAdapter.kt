package com.nanana.covidnow.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.R
import com.nanana.covidnow.UpdateActivity
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rs_item.*

class RsAdapter(
    private val context: Context,
    private var list: List<RSModel>
) :
    RecyclerView.Adapter<RsAdapter.ViewHolder>() {

    var listener: dataListener? = null
    private var auth: FirebaseAuth? = null
    lateinit var ref: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.rs_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))

        holder.itemView.setOnLongClickListener(View.OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("dataNama", list.get(position).nama)
                        bundle.putString("dataTelp", list.get(position).telp)
                        bundle.putString("dataAlamat", list.get(position).alamat)
                        bundle.putString("getPrimaryKey", list.get(position).key)
                        val intent = Intent(view.context, UpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Teman")
                                .child(list.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    tampilToast(view.context, "Data Berhasil Dihapus")
                                }
                        }
                    }
                }
            }
            alert.create()
            alert.show()
            true
        })
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: RSModel) {
            nama.text = item.nama
            alamat.text = item.alamat
            telp.text = item.telp
        }
    }

    fun setData(list: List<RSModel>){
        this.list = list
        notifyDataSetChanged()
    }

    interface dataListener {
        fun onDeleteData(data: RSModel, position: Int)
    }
}