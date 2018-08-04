package com.hightoon.controller.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hightoon.R


class SpringFragment : Fragment() {


    companion object {

        @JvmStatic
        fun newInstance() = SpringFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spring, container, false)
    }



}
