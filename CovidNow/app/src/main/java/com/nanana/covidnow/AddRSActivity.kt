package com.nanana.covidnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanana.covidnow.data.RSModel
import com.nanana.covidnow.util.tampilToast
import com.nanana.covidnow.viewmodel.RSViewModel
import kotlinx.android.synthetic.main.activity_add_r_s.*
import kotlinx.android.synthetic.main.activity_add_r_s.alamat
import kotlinx.android.synthetic.main.activity_add_r_s.nama
import kotlinx.android.synthetic.main.activity_add_r_s.prov
import kotlinx.android.synthetic.main.activity_add_r_s.telp
import kotlinx.android.synthetic.main.rs_item.*

class AddRSActivity : AppCompatActivity() {

    private var Nama: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null

    private val viewModel by viewModels<RSViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_r_s)

        viewModel.init(this)

        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            prosesSave()
        }
    }

    fun prosesSave(){
        val getNama: String = nama.text.toString()
        val getAlamat: String = alamat.text.toString()
        val getTelp: String = telp.text.toString()
        var getProv = prov.text.toString()
        val getUserID: String = auth?.currentUser?.uid.toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()) {
            tampilToast(this, "Data tidak boleh ada yang kosong")
        }else{
            val rs = RSModel(getNama, getAlamat, getTelp, getProv, "")
            ref.child("hospitals").push().setValue(rs).addOnCompleteListener {
                tampilToast(this, "Data Berhasil Disimpan")
                nama.setText("")
                alamat.setText("")
                telp.setText("")
                rs.key = ref.key.toString()
                viewModel.addData(rs)
            }

            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
