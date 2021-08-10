package com.example.andvk.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount:String,
    val sharesCount:String,
    val likedByMe:Boolean = false,
    val sharedByMe:Boolean = false

)

