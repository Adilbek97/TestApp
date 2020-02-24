package com.example.testapp.network

import com.example.testapp.model.Post
import io.reactivex.Observable
import retrofit2.http.*

interface PostApi {
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @POST("/posts")
    @FormUrlEncoded
    fun addStudent(@Field("title")title:String = "",
                   @Field("body")body:String = "",
                   @Field("userId")userId:Int): Observable<Post>
}