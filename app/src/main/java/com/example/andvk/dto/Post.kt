package com.example.andvk.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount:Long = 0,
    val sharesCount:Long = 0,
    val likedByMe:Boolean = false

)

