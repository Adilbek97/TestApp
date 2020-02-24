package com.example.testapp.ui.postsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.model.Post
import com.example.testapp.repository.PostListRepository

class PostsViewModel(private val repository: PostListRepository):ViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<List<Post>>()

    fun loadPostsList(): LiveData<List<Post>> {
        return repository.getPostList()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }
}