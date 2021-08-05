package com.example.andvk

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likeCount:String,
    var likedByMe:Boolean = false

)

