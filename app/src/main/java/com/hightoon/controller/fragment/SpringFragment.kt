package com.hightoon.controller.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.eclipsesource.json.JsonArray
import com.google.gson.Gson
import org.json.simple.JSONArray
import com.hightoon.R
import com.hightoon.Util.RetrofitUtil
import com.hightoon.controller.adapter.HomeRecyclerAdapter
import com.hightoon.controller.data.RecyclerData
import com.hightoon.controller.data.SeasonList
import kotlinx.android.synthetic.main.fragment_spring.view.*
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.LinearLayoutManager



class SpringFragment : Fragment() {
    var item: ArrayList<RecyclerData> = ArrayList()
    var array: JSONArray? = null
    var tmp: JSONObject? = null
    var i = 0
    private lateinit var adapter: HomeRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_spring, container, false)
        var layoutManager = GridLayoutManager(context,2)
        view.springfRecycler.layoutManager = layoutManager

        adapter = HomeRecyclerAdapter(item,context!!)
        view.springfRecycler.adapter = adapter
        var res = RetrofitUtil.postService.Season("봄")
        res.enqueue(object : Callback<SeasonList> {
            override fun onResponse(call: Call<SeasonList>?, response: Response<SeasonList>) {
                if (response.code() == 200) {
                    Log.e("아아아앙", Gson().toJson(response.body()))
                    var parser = JSONParser()
                    var obj = parser.parse(Gson().toJson(response.body())) as JSONObject
                    array = obj.get("list") as JSONArray?
                    Log.e("testsize",Gson().toJson(array))
//                    obj.getString("name")
//                    obj.getString("price")
//                    obj.getString("img")
//                    obj.getString("size")
//                    obj.getString("season")
                    for (i in 0..array!!.size-1) {
                        tmp = array!!.get(i) as JSONObject?
                        if(tmp!!.get("season") as String == "봄") {
                            item.add(RecyclerData(
                                    tmp!!.get("name") as String,
                                    tmp!!.get("price") as String,
                                    tmp!!.get("img") as String,
                                    tmp!!.get("company") as String,
                                    tmp!!.get("size") as String))
                        }
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("로그를 머로찍지", "정보존재x")
                }

            }

            override fun onFailure(call: Call<SeasonList>?, t: Throwable?) {
                Log.e("seasonError", t!!.message)
            }
        })

                return view
    }
}



