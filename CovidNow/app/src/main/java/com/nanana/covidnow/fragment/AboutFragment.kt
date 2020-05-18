package com.nanana.covidnow.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.nanana.covidnow.GejalaActivity
import com.nanana.covidnow.HotlineActivity
import com.nanana.covidnow.KasusActivity

import com.nanana.covidnow.R
import kotlinx.android.synthetic.main.fragment_about.*

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_kasus.setOnClickListener {
            startActivity(Intent(context, KasusActivity::class.java))
        }

        bt_gejala.setOnClickListener {
            startActivity(Intent(context, GejalaActivity::class.java))
        }

        bt_hotline.setOnClickListener {
            startActivity(Intent(context, HotlineActivity::class.java))
        }
    }

}
