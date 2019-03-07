package com.example.initish.kotlinyoutube

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter (val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {

        //Here we create a view
        val layoutInflator = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflator.inflate(R.layout.video_row,p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val title = homeFeed.videos.get(position)
        holder.view.vid_title.text =title.name
        holder.view.vid_ch.text = title.channel.name + " - "+"24K views\n4 days ago"

        Picasso.get().load(title.imageUrl).into(holder.view.vid_pic)
        Picasso.get().load(title.channel.profileImageUrl).into(holder.view.prof_pic)

        holder?.video = title
    }

}

class CustomViewHolder(val view: View, var video: Video? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_LINK = "VIDEO_LINK"
    }

    init {
        view.setOnClickListener {

            val intent = Intent(view.context,VideoClass::class.java)
            intent.putExtra(VIDEO_TITLE_KEY,video?.name)
            view.context.startActivity(intent)
        }
    }

}