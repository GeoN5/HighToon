package com.hightoon.controller.data

import com.google.gson.JsonElement

class RecyclerData(name: String, price: String, img: String,company : String, size : String){
    var name = ""
    var price = ""
    var img = ""
    var company = ""
    var size = ""
    init {
        this.name = name
        this.price = price
        this.img = img
        this.size = size
        this.company = company
    }
}