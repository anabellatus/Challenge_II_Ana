package com.nanana.covidnow.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanana.covidnow.KasusActivity

import com.nanana.covidnow.R
import com.nanana.covidnow.adapter.IndoAdapter
import com.nanana.covidnow.data.APIService
import com.nanana.covidnow.data.DataIndoItem
import com.nanana.covidnow.data.apiRequest
import com.nanana.covidnow.data.httpClient
import com.nanana.covidnow.util.dismissLoading
import com.nanana.covidnow.util.showLoading
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetDataIndo()
        bt_kasus.setOnClickListener {
            startActivity(Intent(context, KasusActivity::class.java))
        }
    }

    private fun callApiGetDataIndo() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<APIService>(httpClient)

        val call = apiRequest.getIndo()
        call.enqueue(object : Callback<List<DataIndoItem>> {
            override fun onFailure(call: Call<List<DataIndoItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<DataIndoItem>>,
                response: Response<List<DataIndoItem>>
            ) {
                dismissLoading(swipeRefreshLayout)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()!!.size != 0 ->
                                tampilDataIndo(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }

        })
    }

    private fun tampilDataIndo(dataIndo: List<DataIndoItem>) {
        rv_indo.layoutManager = LinearLayoutManager(context)
        rv_indo.adapter = IndoAdapter(context!!, dataIndo) {
            val dataIndo = it
            tampilToast(context!!, dataIndo.name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
