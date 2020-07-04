package com.nanana.covidnow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar.visibility = View.GONE

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        login.setOnClickListener{
            loginWithEmail()
        }

        login_google.setOnClickListener {
            loginWithGoogle()
        }
        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser == null){
        }else{
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginWithGoogle(){
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
        progressBar.visibility = View.VISIBLE
    }

    private fun loginWithEmail(){
        var email = et_email.text.toString()
        var pass = et_password.text.toString()

        if (!email.isEmpty() && !pass.isEmpty()){
            progressBar.visibility = View.VISIBLE
            auth?.signInWithEmailAndPassword(email, pass)!!.addOnCompleteListener(this) {
                if (it.isSuccessful){
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }else{
                    tampilToast(this@LoginActivity, "Login gagal" +it.exception.toString())
                }
            }
        }else{
            tampilToast(this, "masukkan email/password")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            try {

                if (requestCode == RC_SIGN_IN) {
                    if (resultCode == Activity.RESULT_OK) {
                        tampilToast(this, "Login Berhasil")
                        intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        progressBar.visibility = View.GONE
                        tampilToast(this, "Login Dibatalkan")
                    }
                }

            }catch (e: Exception){
                tampilToast(this, e.message.toString())
        }
    }
}
