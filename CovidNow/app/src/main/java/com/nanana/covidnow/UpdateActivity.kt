package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar?.setTitle("Update Data")
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        getaData()
        simpan.setOnClickListener {
            var namaBaru = nama.text.toString()
            var alamatBaru = alamat.text.toString()
            var telpBaru = telp.text.toString()

            if (namaBaru.isEmpty() || alamatBaru.isEmpty() || telpBaru.isEmpty()) {
                tampilToast(this@UpdateActivity, "Data tidak boleh ada yang kosong")
            } else {
                val temanBaru = RSModel(namaBaru, alamatBaru, telpBaru, "")
                val getUserID: String = auth!!.currentUser!!.uid.toString()
                val getKey: String = intent.getStringExtra("getPrimaryKey").toString()
                database!!.child(getUserID).child("Teman").child(getKey).setValue(temanBaru)
                    .addOnCompleteListener {
                        tampilToast(this@UpdateActivity, "Data Berhasil Disimpan")
                        finish()
                    }
            }
        }
    }

    private fun getaData(){
        val getNama: String  = intent.getStringExtra("dataNama").toString()
        val getTelp: String  = intent.extras!!.getString("dataTelp").toString()
        val getAlamat: String  = intent.extras!!.getString("dataAlamat").toString()
        nama.setText(getNama)
        telp.setText(getTelp)
        alamat.setText(getAlamat)
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()
    }
}
