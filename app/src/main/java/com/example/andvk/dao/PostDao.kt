package com.example.andvk.dao

import com.example.andvk.dto.Post
import java.io.Closeable

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun shareById(id: Long)
}