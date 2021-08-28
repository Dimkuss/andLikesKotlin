package com.example.andvk.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount:Long,
    val sharesCount:Long,
    val likedByMe:Boolean = false

)

