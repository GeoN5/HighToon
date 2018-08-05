package com.hightoon.controller.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hightoon.R
import com.hightoon.controller.activity.SeleterActivity
import com.hightoon.controller.data.RecyclerData

class HomeRecyclerAdapter(items: ArrayList<RecyclerData>, context: Context) : RecyclerView.Adapter<ViewHolder_>() {
    var items: ArrayList<RecyclerData> = ArrayList()
    var adapterContext: Context? = null


    init {
        this.items = items
        this.adapterContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_ {
        val view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.home_item, parent, false)
        return ViewHolder_(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder_, position: Int) {

        var intent = Intent(adapterContext, SeleterActivity::class.java)
        var data = items[position]
            holder.name.text = data.name
            Glide.with(adapterContext!!).load(data.img).into(holder.img)
            holder.price.text = data.price

        holder.itemView.setOnClickListener  {
            intent.putExtra("name",data.name)
            intent.putExtra("company",data.company)
            intent.putExtra("size",data.size)
            intent.putExtra("img",data.img)
            intent.putExtra("price",data.price)
            adapterContext!!.startActivity(intent)
        }

    }
}


class ViewHolder_(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name: TextView = itemView.findViewById(R.id.homeName)
    var img: ImageView = itemView.findViewById(R.id.homeImg)
    var price: TextView = itemView.findViewById(R.id.homePrice)
}