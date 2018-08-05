package com.hightoon.controller.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hightoon.R
import com.hightoon.controller.data.locker
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.locker_list.*
import kotlinx.android.synthetic.main.locker_list.view.*


interface OnItemClickListener {
    fun onItemDelete(position: Int)
    fun onItemBuy(position: Int)
}

class ViewHolder(override val containerView: View, val listener: OnItemClickListener) : RecyclerView.ViewHolder(containerView) , LayoutContainer {

    fun bind(item: locker) {
        company.text = item.company
        size.text = item.size
        title.text = item.title
        price.text = item.price

        delete.setOnClickListener {
            listener.onItemDelete(adapterPosition)
        }
        buy.setOnClickListener {
            listener.onItemBuy(adapterPosition)
        }
    }
}

class RecyclerAdapter(val items: List<locker>, val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.locker_list, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = items[position]
        Glide.with(context).load(data.image).into(holder.itemView.image)
        holder.bind(items[position])
    }
}