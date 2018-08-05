package com.hightoon.controller.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.hightoon.Util.RetrofitUtil
import com.hightoon.controller.fragment.FallFragment
import com.hightoon.controller.fragment.SpringFragment
import com.hightoon.controller.fragment.SummerFragment
import com.hightoon.controller.fragment.WinterFragment

class HomeAdapter(fm : FragmentManager?) : FragmentPagerAdapter(fm) {

    var fragment = Fragment()
    private val MAX_PAGE = 4
    override fun getItem(position: Int): Fragment? {
        if(position<0 || MAX_PAGE<=position)
            return null

        Log.e("position",position.toString())

        when (position) {
            0 -> {
                fragment = SpringFragment()
            }

            1 -> {
                fragment = SummerFragment()
            }

            2 -> {
                fragment = FallFragment()
            }

            3 -> {
                fragment = WinterFragment()
            }
        }
        return fragment
    }

    override fun getCount(): Int {
        return MAX_PAGE
    }

}