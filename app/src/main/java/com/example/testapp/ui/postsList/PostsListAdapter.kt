package com.example.testapp.ui.postsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostsListAdapter(private val interaction: (Post) -> Unit): ListAdapter<Post, PostsListAdapter.ViewHolder>(PostDC())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(post: Post){
            itemView.title_tv.text = post.title
            itemView.setOnClickListener {
                interaction(post)
            }
        }
    }

    fun swapData(data:List<Post>){
        submitList(data.toMutableList())
    }

    private class PostDC : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(
            oldItem: Post,
            newItem: Post
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Post,
            newItem: Post
        ): Boolean {
            return oldItem == newItem
        }
    }

}