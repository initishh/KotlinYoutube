   package com.example.initish.kotlinyoutube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

   class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // rcv_main.setBackgroundColor(Color.BLUE)

        rcv_main.layoutManager = LinearLayoutManager(this)

//        swipe_layout.setOnRefreshListener(
//            object: SwipeRefreshLayout.OnRefreshListener {
//                override fun onRefresh() {
//                    // This method performs the actual data-refresh operation.
//                    // The method calls setRefreshing(false) when it's finished.
//                    fetchJson()
//                }
//            }
//        )
        fetchJson()
    }



    fun fetchJson() {
        println("Attempting to fetch json")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to load Json!!!")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body,HomeFeed::class.java)

                runOnUiThread {
                    rcv_main.adapter = MainAdapter(homeFeed)
                }
            }

        })
    }
}
