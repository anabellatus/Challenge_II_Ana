package com.nanana.covidnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.nanana.covidnow.adapter.RsAdapter
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import com.nanana.covidnow.viewmodel.RSActivityViewModel
import kotlinx.android.synthetic.main.activity_rs.*

class RsActivity : AppCompatActivity(), RsAdapter.dataListener {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
//    lateinit var dataTeman : ArrayList<RSModel>

    var dataTeman: MutableList<RSModel> = ArrayList()
    private val viewModel by viewModels<RSActivityViewModel>()
    private var adapter: RsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rs)

        getData()

        rv_rs.layoutManager = LinearLayoutManager(this)
        adapter = RsAdapter(this, dataTeman)
        rv_rs.adapter = adapter
        adapter?.listener = this

        viewModel.init(this)
        viewModel.allRS.observe(this, Observer { hospitals ->
            hospitals?.let { adapter?.setData(it) }
        })

        fab.setOnClickListener {
            val intent = Intent (this, AddRSActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData(){
        tampilToast(this, "Mohon tunggu sebentar.....")
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth.currentUser?.uid.toString()
        ref  =FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                tampilToast(this@RsActivity, "Database Error yaa...")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataTeman = ArrayList()
                for (snapshot in dataSnapshot.children) {

                    val teman = snapshot.getValue(RSModel::class.java)
                    teman?.key = (snapshot.key!!)
                    dataTeman.add(teman!!)
                }
                viewModel.insertAll(dataTeman)

//                dataTeman = java.util.ArrayList<RSModel>()
//                for(snapshot in dataSnapshot.children) {
//                    val teman = snapshot.getValue(RSModel::class.java)
//
//                    teman?.key= (snapshot.key!!)
//                    dataTeman.add(teman!!)
//                }
//                rv_rs.layoutManager = LinearLayoutManager(this@RsActivity)
//                rv_rs.adapter = RsAdapter(this@RsActivity, dataTeman)
//
//                tampilToast(this@RsActivity, "Data Berhasil Dimuat")

            }
        })
    }

    override fun onDeleteData(data: RSModel, position: Int) {

        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        if (ref != null) {
            ref.child(getUserID)
                .child("Teman")
                .child(data?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this@RsActivity, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(this@RsActivity, data!!.key!!.toString(), Toast.LENGTH_LONG).show()
        }
    }

}
