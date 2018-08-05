package com.hightoon.controller.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hightoon.R
import com.hightoon.controller.adapter.HomeRecyclerAdapter
import com.hightoon.controller.adapter.RecyclerAdapter
import com.hightoon.controller.data.locker
import kotlinx.android.synthetic.main.activity_seleter.*
import kotlinx.android.synthetic.main.fragment_locker.*
import kotlinx.android.synthetic.main.fragment_spring.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class SeleterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleter)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var intent = intent
        var name = intent.getStringExtra("name")
        var price = intent.getStringExtra("price")
        var img = intent.getStringExtra("img")
        var company = intent.getStringExtra("company")
        var size = intent.getStringExtra("size")

        Glide.with(this).load(img).into(seleterImg)
        seletercompany.text = "회사 : " + company
        seletername.text =   name
        seleterprice.text =  "가격 : " + price
        seletersize.text =  "사이즈 : " + size

        seleterbuy.onClick {
            Toast.makeText(applicationContext,name + "을 구매 하였습니다",Toast.LENGTH_SHORT).show()
        }

        seleterlocker.onClick {
            Toast.makeText(applicationContext,name + "가 장바구니에 추가되었습니다",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
