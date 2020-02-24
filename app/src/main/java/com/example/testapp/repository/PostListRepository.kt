package com.example.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.model.Post
import com.example.testapp.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostListRepository(private val postApi:PostApi) {
    private lateinit var subscription: Disposable

    fun getPostList():LiveData<List<Post>>{
        val postsList = MutableLiveData<List<Post>>()
        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    postsList.postValue(result)
                }, {}
            )
        return postsList
    }

    fun clear(){
        subscription.dispose()
    }
}