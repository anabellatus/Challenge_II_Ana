package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.nanana.covidnow.adapter.GejalaAdapter
import com.nanana.covidnow.data.GejalaModel
import com.nanana.covidnow.util.tampilToast
import com.nanana.covidnow.viewmodel.GejalaActivityViewModel

class GejalaActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth

    var dataGejala: MutableList<GejalaModel> = ArrayList()
    private val viewModel by viewModels<GejalaActivityViewModel>()
    private var adapter: GejalaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gejala)

//        getData()

//        rv_rs.layoutManager = LinearLayoutManager(this)
//        adapter = RsAdapter(this, dataRs)
//        rv_rs.adapter = adapter
//        adapter?.listener = this
//
//        viewModel.init(this)
//        viewModel.allRS.observe(this, Observer { hospitals ->
//            hospitals?.let { adapter?.setData(it) }
//        })

    }


    private fun getData(){
        tampilToast(this, "Mohon tunggu sebentar.....")
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth.currentUser?.uid.toString()
        ref  = FirebaseDatabase.getInstance().reference
        ref.child("gejala").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                tampilToast(this@GejalaActivity, "Database Error yaa...")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataGejala = ArrayList()
                for (snapshot in dataSnapshot.children) {

                    val gejala = snapshot.getValue(GejalaModel::class.java)
                    gejala?.key = (snapshot.key!!)
                    dataGejala.add(gejala!!)
                }
                viewModel.insertAll(dataGejala)

            }
        })
    }
}
