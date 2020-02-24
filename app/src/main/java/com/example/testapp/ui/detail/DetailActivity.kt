package com.example.testapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testapp.R
import com.example.testapp.model.Post
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent.extras?.let {
            val post = it.getParcelable<Post>("currentPost")
            post?.let {
                title_tv.text = post.title
                description_tv.text = post.body
                user_id_tv.text = post.userId.toString()
            }

        }
    }

}
