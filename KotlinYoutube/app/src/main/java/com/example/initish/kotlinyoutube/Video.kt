package com.example.initish.kotlinyoutube

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.video_layout.*

class VideoClass: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_layout)

        val navbar_title = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navbar_title

        val videoLink = "https://www.youtube.com/watch?v=J8BqZ4_m2a4"
        val uri: Uri = Uri.parse(videoLink)
        vid_id.loadUrl(videoLink)
        // vid_id.start()
    }
}