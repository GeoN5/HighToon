package com.hightoon.controller.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.hightoon.R
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.hightoon.controller.fragment.SeasonFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val FINSH_INTERVAL_TIME = 2000
    private var backPressedTime:Long = 0
    lateinit var fragment : Fragment
    lateinit var fragmentManager: FragmentManager

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.getItemId()) {
                R.id.action_main -> {
                    fragment = SeasonFragment.newInstance()
                }
                R.id.action_cody -> {

                }
                R.id.action_sale -> {

                }
                R.id.action_locker ->{

                }
            }
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.content, fragment).commit()
            return true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init(){
        fragmentManager = supportFragmentManager
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fragment = SeasonFragment.newInstance()
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment).commit()

    }

    override fun onBackPressed() {
        var tempTime = System.currentTimeMillis()
        var intervalTime = tempTime - backPressedTime
        if (intervalTime in 0..FINSH_INTERVAL_TIME) {
            ActivityCompat.finishAffinity(this);
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

}