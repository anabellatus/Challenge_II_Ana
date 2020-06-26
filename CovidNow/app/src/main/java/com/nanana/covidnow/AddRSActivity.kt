package com.nanana.covidnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.main.activity_add_r_s.*

class AddRSActivity : AppCompatActivity() {

    private var Nama: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_r_s)

        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            prosesSave()
        }
    }

    fun prosesSave(){
        val getNama: String = nama.getText().toString()
        val getAlamat: String = alamat.getText().toString()
        val getTelp: String = telp.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()) {
            tampilToast(this, "Data tidak boleh ada yang kosong")
        }else{
            val teman = RSModel(getNama, getAlamat, getTelp, "")
            ref.child(getUserID).child("Teman").push().setValue(teman).addOnCompleteListener {
                tampilToast(this, "Data Berhasil Disimpan")
            }

            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
