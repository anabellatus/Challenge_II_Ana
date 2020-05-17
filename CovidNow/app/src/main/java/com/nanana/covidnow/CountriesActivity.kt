package com.nanana.covidnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanana.covidnow.adapter.CountriesAdapter
import com.nanana.covidnow.data.APIService
import com.nanana.covidnow.data.DataCountriesItem
import com.nanana.covidnow.data.apiCoronaRequest
import com.nanana.covidnow.data.httpClient
import com.nanana.covidnow.util.dismissLoading
import com.nanana.covidnow.util.showLoading
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.main.activity_countries.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        callApiGetDataCountries()
        Log.d("LOGLOG", "onCreate After calling function")
    }

    private fun callApiGetDataCountries(){
        showLoading(this, swipeRefreshLayout)

        Log.d("LOGLOG", "oncallApiGEtData")

        val httpClient = httpClient()
        val apiRequest = apiCoronaRequest<APIService>(httpClient)
        val call = apiRequest.getCountry()
        Log.d("LOGLOG", "Before call.enqueue")
        call.enqueue(object : Callback<List<DataCountriesItem>>{
            override fun onFailure(call: Call<List<DataCountriesItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                Log.d("LOGLOG", "onFailure "+t.message)
            }

            override fun onResponse(
                call: Call<List<DataCountriesItem>>,
                response: Response<List<DataCountriesItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                Log.d("LOGLOG", "onResponse")

                when {
                    response.isSuccessful ->
                        when {
                            response.body()!!.size != 0 ->
                                tampilDataCountry(response.body()!!)
                            else -> {
                                tampilToast(this@CountriesActivity, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(this@CountriesActivity, "Gagal")
                    }
                }
            }

        })
    }

    private fun tampilDataCountry(dataCountry: List<DataCountriesItem>){
        Log.d("LOGLOG", "ontampilDataCountry")
        rv_countries.layoutManager = LinearLayoutManager(this)
        rv_countries.adapter = CountriesAdapter(this, dataCountry){
            val dataCountry = it
            tampilToast(this, dataCountry.country)
        }
    }
}
