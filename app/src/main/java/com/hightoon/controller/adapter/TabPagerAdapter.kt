package com.hightoon.controller.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.hightoon.controller.fragment.FallFragment
import com.hightoon.controller.fragment.SpringFragment
import com.hightoon.controller.fragment.SummerFragment
import com.hightoon.controller.fragment.WinterFragment

class TabPagerAdapter(val fm: FragmentManager,var tabCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return SpringFragment.newInstance()
            }
            1 -> {
                return SummerFragment.newInstance()
            }
            2 -> {
                return FallFragment.newInstance()
            }
            3 -> {
                return WinterFragment.newInstance()
            }
            else -> {
                return null
            }

        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}

