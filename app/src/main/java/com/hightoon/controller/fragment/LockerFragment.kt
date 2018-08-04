package com.hightoon.controller.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.hightoon.R
import com.hightoon.controller.adapter.OnItemClickListener
import com.hightoon.controller.adapter.RecyclerAdapter
import com.hightoon.controller.data.locker
import kotlinx.android.synthetic.main.fragment_locker.*
import kotlinx.android.synthetic.main.fragment_locker.view.*


class LockerFragment : Fragment() {

    lateinit var fragmentview: View
    var items :MutableList<locker> = ArrayList()

    companion object {

        @JvmStatic
        fun newInstance() = LockerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentview = inflater.inflate(R.layout.fragment_locker, container, false)
        adddummy()
        setrecyclerview()
        setListeners()
        return fragmentview
    }

    fun adddummy(){
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","hightoon","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","highasdn","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","asdasdn","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","hiasdsadasdon","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","hiasdsadasdon","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","hiasdsadasdon","XL","jacket","50,000"))
        items.add(locker("http://iwin247.kr:3030/img/NhvbPKX7k6is7y6kh6JFPkrMuGRFBUdI4.PNG","hiasdsadasdon","XL","jacket","50,000"))
    }

    fun setrecyclerview(){
        fragmentview.recyclerview.layoutManager = LinearLayoutManager(context!!)
        fragmentview.recyclerview.setHasFixedSize(true)
        fragmentview.recyclerview.adapter = RecyclerAdapter(items, context!!,object : OnItemClickListener{
            override fun onItemBuy(position: Int) {
               items.removeAt(position)
                fragmentview.recyclerview.adapter.notifyDataSetChanged()
                Toast.makeText(context,"상품 주문이 완료되었습니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onItemDelete(position: Int) {
                items.removeAt(position)
               // fragmentview.recyclerview.adapter.notifyItemRemoved(position)
                fragmentview.recyclerview.adapter.notifyDataSetChanged()
                Toast.makeText(context,"해당 상품을 삭제하였습니다.",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun setListeners(){
        fragmentview.deleteall.setOnClickListener {
            fragmentview.visibility = View.GONE
            Toast.makeText(context,"장바구니 목록을 삭제하였습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    
}
