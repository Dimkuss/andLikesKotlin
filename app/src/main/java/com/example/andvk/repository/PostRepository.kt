package com.example.andvk.repository

import androidx.lifecycle.LiveData
import com.example.andvk.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()

}