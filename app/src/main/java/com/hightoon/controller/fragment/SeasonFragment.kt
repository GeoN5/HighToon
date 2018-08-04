package com.hightoon.controller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hightoon.R




class SeasonFragment : Fragment() {

    lateinit var fragmentView : View


    companion object {

        @JvmStatic
        fun newInstance() = SeasonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentView =  inflater.inflate(R.layout.fragment_season, container, false)
        loadData()
        return fragmentView
    }

    fun loadData(){

    }

}
