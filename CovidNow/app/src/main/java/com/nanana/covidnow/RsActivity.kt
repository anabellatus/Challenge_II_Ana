package com.nanana.covidnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.nanana.covidnow.adapter.RsAdapter
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.main.activity_rs.*

class RsActivity : AppCompatActivity(), RsAdapter.dataListener {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTeman : ArrayList<RSModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rs)

        getData()
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
                dataTeman = java.util.ArrayList<RSModel>()
                for(snapshot in dataSnapshot.children) {
                    val teman = snapshot.getValue(RSModel::class.java)

                    teman?.key= (snapshot.key!!)
                    dataTeman.add(teman!!)
                }
                rv_rs.layoutManager = LinearLayoutManager(this@RsActivity)
                rv_rs.adapter = RsAdapter(this@RsActivity, dataTeman)

                tampilToast(this@RsActivity, "Data Berhasil Dimuat")

            }

        })
    }
}
