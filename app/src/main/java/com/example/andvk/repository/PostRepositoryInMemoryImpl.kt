package com.example.andvk.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.andvk.dto.Post
import kotlinx.android.synthetic.main.activity_main.*

class PostRepositoryInMemoryImpl : PostRepository{
    private var post = Post(
    id = 1,
    author = "Netologiya Universitet internet professiy budushego",
    content = "Привет это Нетология, к сожалению я не нашел полного текста в ресурсах ДЗ, а с экрана переписывать мне не хоче" +
    "тся, по этому я тут просто напишу много слов и думаю, что этого будет достаточно, мои дела отлично, в конце лета двигаюсь в СПБ к другу, буду готовиться" +
    " к собеседованиям на работу, ах точно, вот тут еще сайт, посетите google.com",
    published = "30 July at 16:30",
    likeCount = "9998",
    sharesCount = "1",
    likedByMe = false

    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
//        var counter = post.likeCount.toInt()
//
//        if (post.likedByMe) {
//            counter++
//
//            when (counter) {
//                in 0..999 -> post.copy(likeCount = "$counter" )
////                in 1000..1099 -> textLikes.text = "${counter / 1000}K"
////
////                in 1100..9999 -> textLikes.text = "${counter.toDouble() / 1000}K"
////                in 10000..999_999 -> textLikes.text = "${counter.toDouble() / 1000}K"
////                in 1_000_000..1_099_999 -> textLikes.text = "${counter / 1000000}M"
////                in 1_100_000..999_999_999 -> textLikes.text =
////                    "${counter.toDouble() / 1000000}M"
//
//            }
//
//        } else {
//            counter - 1
////textLikes.text = "$counter"
//            when (counter) {
//                in 0..999 -> post.copy(likeCount = "$counter" )
////                in 1000..1099 -> textLikes.text = "${counter / 1000}K"
////                in 1100..9999 -> textLikes.text = "${counter.toDouble() / 1000}K"
////                in 10000..999_999 -> textLikes.text = "${counter / 1000}K"
////                in 1_000_000..1_099_999 -> textLikes.text = "${counter / 1000000}M"
////                in 1_100_000..999_999_999 -> textLikes.text =
////                    "${counter.toDouble() / 1000000}M"
//
//            }
//        }
    }

}