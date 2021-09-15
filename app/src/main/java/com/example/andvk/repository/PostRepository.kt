package com.example.andvk.repository

import androidx.lifecycle.LiveData
import com.example.andvk.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id:Long)
    fun save(post: Post)
    fun discardEdit(post: Post)


}