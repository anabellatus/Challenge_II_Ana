package com.nanana.covidnow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanana.covidnow.R
import com.nanana.covidnow.adapter.ProvinceAdapter
import com.nanana.covidnow.data.*
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
        return inflater.inflate(R.layout.fragment_discover, container, false)

    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetDataProvince()
    }

    private fun callApiGetDataProvince() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<APIService>(httpClient)

        val call = apiRequest.getProvince()
        call.enqueue(object : Callback<List<DataProvinceItem>> {
            override fun onFailure(call: Call<List<DataProvinceItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<DataProvinceItem>>,
                response: Response<List<DataProvinceItem>>
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

    private fun tampilDataProvince(dataProvince: List<DataProvinceItem>) {
        rv_province_list.layoutManager = LinearLayoutManager(context)
        rv_province_list.adapter = ProvinceAdapter(context!!, dataProvince) {
            val dataProvince = it
            tampilToast(context!!, dataProvince.attributes.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
