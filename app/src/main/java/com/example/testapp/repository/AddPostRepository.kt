package com.example.testapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.model.Post
import com.example.testapp.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddPostRepository(private val postApi: PostApi){
    private lateinit var subscription: Disposable
    fun addPost(title:String,body:String,userId:Int):LiveData<AddingStatus>{
        val addingState = MutableLiveData<AddingStatus>()
        val subscription = postApi.addStudent(title = title,body = body,userId = userId)
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addingState.postValue(AddingStatus.AddedSuccesfully)
                },
                {
                    addingState.postValue(AddingStatus.AddingError)
                }
            )
        return addingState
    }
    enum class AddingStatus{
        AddedSuccesfully,AddingError
    }

    fun clear(){
        subscription.dispose()
    }
}