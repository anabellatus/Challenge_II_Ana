package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import com.nanana.covidnow.viewmodel.RSUpdateViewModel
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.alamat
import kotlinx.android.synthetic.main.activity_update.nama
import kotlinx.android.synthetic.main.activity_update.telp

class UpdateActivity : AppCompatActivity() {

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null

    private val viewModel by viewModels<RSUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        getaData()
        simpan.setOnClickListener {
            var namaBaru = nama.text.toString()
            var alamatBaru = alamat.text.toString()
            var telpBaru = telp.text.toString()
            var provBaru = prov.text.toString()

            if (namaBaru.isEmpty() || alamatBaru.isEmpty() || telpBaru.isEmpty() || provBaru.isEmpty()) {
                tampilToast(this@UpdateActivity, "Data tidak boleh ada yang kosong")
            } else {
                val rsBaru = RSModel(namaBaru, alamatBaru, telpBaru, provBaru, "")
                val getUserID: String = auth!!.currentUser!!.uid.toString()
                val getKey: String = intent.getStringExtra("getPrimaryKey").toString()
                database!!.child("hospitals")
//                    .child("Teman")
                    .child(getKey).setValue(rsBaru)
                    .addOnCompleteListener {
                        viewModel.updateData(rsBaru)
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
        val getProv: String  = intent.extras!!.getString("dataProvinsi").toString()
        nama.setText(getNama)
        telp.setText(getTelp)
        alamat.setText(getAlamat)
        prov.setText(getProv)
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()
    }
}
