package com.nanana.covidnow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.nanana.covidnow.adapter.HotlineAdapter
import com.nanana.covidnow.data.HotlineModel
import com.nanana.covidnow.util.tampilToast
import com.nanana.covidnow.viewmodel.HotlineActivityViewModel
import kotlinx.android.synthetic.main.activity_hotline.*

class HotlineActivity: AppCompatActivity(), HotlineAdapter.dataListener  {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth

    var dataHotline: MutableList<HotlineModel> = ArrayList()
    private val viewModel by viewModels<HotlineActivityViewModel>()
    private var adapter: HotlineAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotline)

        getData()

        rv_hotline.layoutManager = LinearLayoutManager(this)
        adapter = HotlineAdapter(this, dataHotline)
        rv_hotline.adapter = adapter
        adapter?.listener = this

        viewModel.init(this)
        viewModel.allHotline.observe(this, Observer { hospitals ->
            hospitals?.let { adapter?.setData(it) }
        })
    }

    private fun getData(){
        tampilToast(this, "Mohon tunggu sebentar.....")
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth.currentUser?.uid.toString()
        ref  = FirebaseDatabase.getInstance().reference
        ref.child("hotline").addValueEventListener(object : ValueEventListener {
            //        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                tampilToast(this@HotlineActivity, "Database Error yaa...")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataHotline = ArrayList()
                for (snapshot in dataSnapshot.children) {

                    val rs = snapshot.getValue(HotlineModel::class.java)
                    rs?.key = (snapshot.key!!)
                    dataHotline.add(rs!!)
                }
                viewModel.insertAll(dataHotline)

            }
        })
    }

    override fun onDeleteData(data: HotlineModel, position: Int) {
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.currentUser?.uid.toString()
        if (ref != null) {
            ref.child("hotline")
                .child(data?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this@HotlineActivity, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(this@HotlineActivity, data!!.key!!.toString(), Toast.LENGTH_LONG).show()
        }
    }
    }

}