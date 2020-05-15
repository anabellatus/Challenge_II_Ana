package com.nanana.covidnow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanana.covidnow.R
import com.nanana.covidnow.adapter.ProvinceAdapter
import com.nanana.covidnow.data.APIService
import com.nanana.covidnow.data.Attributes
import com.nanana.covidnow.data.apiRequest
import com.nanana.covidnow.data.httpClient
import com.nanana.covidnow.util.dismissLoading
import com.nanana.covidnow.util.showLoading
import com.nanana.covidnow.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_discover.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
        callApiGetDataProvince()
    }

    private fun callApiGetDataProvince() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<APIService>(httpClient)

        val call = apiRequest.getProvince()
        call.enqueue(object : Callback<List<Attributes>> {
            override fun onFailure(call: Call<List<Attributes>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<Attributes>>,
                response: Response<List<Attributes>>
            ) {
                dismissLoading(swipeRefreshLayout)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()!!.size != 0 ->
                                tampilDataProvince(response.body()!!)
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

    private fun tampilDataProvince(dataProvince: List<Attributes>) {
        rv_province_list.layoutManager = LinearLayoutManager(context)
        rv_province_list.adapter = ProvinceAdapter(context!!, dataProvince) {
            val dataProvince = it
            tampilToast(context!!, dataProvince.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
