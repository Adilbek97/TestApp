package com.example.testapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.repository.AddPostRepository

class AddPostViewModel(private val addPostRepository: AddPostRepository):ViewModel() {

    fun addPost(title:String,body:String,userId:Int):LiveData<AddPostRepository.AddingStatus>{
        return addPostRepository.addPost(title, body, userId)
    }


}