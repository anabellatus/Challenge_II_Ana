package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanana.covidnow.adapter.KasusAdapter
import com.nanana.covidnow.data.*
import com.nanana.covidnow.util.dismissLoading
import com.nanana.covidnow.util.showLoading
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_kasus.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KasusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kasus)
        callApiGetDataKasus()
    }

    private fun callApiGetDataKasus(){
        showLoading(this, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apimathdroRequest<APIService>(httpClient)
        val call = apiRequest.getKasus()
        call.enqueue(object : Callback<List<Data>>{
            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                dismissLoading(swipeRefreshLayout)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()!!.size != 0 ->
                                tampilDataKasus(response.body()!!)
                            else -> {
                                tampilToast(this@KasusActivity!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(this@KasusActivity!!, "Gagal")
                    }
                }
            }

        })
    }

    private fun tampilDataKasus(dataKasus: List<Data>){
        rv_kasus.layoutManager = LinearLayoutManager(this)
        rv_kasus.adapter = KasusAdapter(this, dataKasus){
            val dataKasus = it
            tampilToast(this, dataKasus.kodePasien.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
