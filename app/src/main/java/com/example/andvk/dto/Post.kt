package com.example.andvk.dto
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likeCount:Long = 0,
    val sharesCount:Long = 0,
    val likedByMe:Boolean = false

) : Parcelable

