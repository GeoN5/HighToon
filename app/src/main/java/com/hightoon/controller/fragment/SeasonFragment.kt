package com.hightoon.controller.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hightoon.R
import com.hightoon.controller.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_season.view.*
import android.support.v4.view.ViewPager
import android.util.Log
import kotlinx.android.synthetic.main.fragment_season.*
import org.jetbrains.anko.support.v4.onPageChangeListener


@SuppressLint("ValidFragment")
class SeasonFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = SeasonFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        Log.e("hi",fm.toString())
        var view  = inflater!!.inflate(R.layout.fragment_season,container,false)

        val adapter = HomeAdapter(childFragmentManager)
        view.seasonViewpager.adapter = adapter
        view.seasonViewpager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Log.e("pos", position.toString())
            }

        })

        return view
    }
}