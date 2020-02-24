package com.example.testapp.di

import com.example.testapp.network.PostApi
import com.example.testapp.network.RetrofitService
import com.example.testapp.repository.AddPostRepository
import com.example.testapp.repository.PostListRepository
import com.example.testapp.ui.add.AddPostViewModel
import com.example.testapp.ui.postsList.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { RetrofitService.builService(PostApi::class.java) }
    single { PostListRepository(get()) }
    single { AddPostRepository(get()) }

    viewModel { PostsViewModel(get()) }
    viewModel { AddPostViewModel(get()) }
}