package com.example.andvk.viewmodel

import androidx.lifecycle.ViewModel
import com.example.andvk.repository.PostRepository
import com.example.andvk.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
}