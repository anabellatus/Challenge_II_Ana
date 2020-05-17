package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        Log.d("LOGLOG", "OnCreate after callApiGetData function")
    }

    private fun callApiGetDataKasus(){
        showLoading(this, swipeRefreshLayout)

        Log.d("LOGLOG", "On callApiGetData")

        val httpClient = httpClient()
        val apiRequest = apimathdroRequest<APIService>(httpClient)
        val call = apiRequest.getKasus()
        Log.d("LOGLOG", "Before Call.enqueue")
        call.enqueue(object : Callback<List<DataKasus>>{
            override fun onFailure(call: Call<List<DataKasus>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                Log.d("LOGLOG", "OnFailure"+t)
            }

            override fun onResponse(call: Call<List<DataKasus>>, response: Response<List<DataKasus>>) {
                dismissLoading(swipeRefreshLayout)
                Log.d("LOGLOG", "OnResponse")

                when {
                    response.isSuccessful ->
                        when {
                            response.body()!!.size != 0 ->
                                tampilDataKasus(response.body()!!)
                            else -> {
                                tampilToast(this@KasusActivity, "Berhasil")
                                Log.d("LOGLOG", "OnResponse Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(this@KasusActivity, "Gagal")
                        Log.d("LOGLOG", "OnResponse Gagal")
                    }
                }
            }

        })
    }

    private fun tampilDataKasus(dataKasus: List<DataKasus>){
        rv_kasus.layoutManager = LinearLayoutManager(this)
        rv_kasus.adapter = KasusAdapter(this, dataKasus){
            val dataKasus = it
            tampilToast(this, dataKasus.data.kodePasien.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
