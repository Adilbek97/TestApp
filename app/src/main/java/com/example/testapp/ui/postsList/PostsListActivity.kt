package com.example.testapp.ui.postsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.hide
import com.example.testapp.ui.add.AddPostActivity
import com.example.testapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsListActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private val postViewModel: PostsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPostsList()
        fab.setOnClickListener {
            startActivity(Intent(this,AddPostActivity::class.java))
        }
    }

    private fun setPostsList() {
        val postsListAdapter = PostsListAdapter{post ->
            startActivity(Intent(this,DetailActivity::class.java).putExtra("currentPost",post))
        }

        posts_rv.apply {
            layoutManager = LinearLayoutManager(this@PostsListActivity, LinearLayoutManager.VERTICAL,false)
            adapter = postsListAdapter
        }

        postViewModel.loadPostsList().observe(this, Observer {postsList ->
            if (!postsList.isNullOrEmpty()){
                postsListAdapter.swapData(postsList)
                progress_bar.hide()
            }
        })

    }

}
