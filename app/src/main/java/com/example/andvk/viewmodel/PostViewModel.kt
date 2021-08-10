package com.example.andvk.viewmodel

import androidx.lifecycle.ViewModel
import com.example.andvk.repository.PostRepository
import com.example.andvk.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
}