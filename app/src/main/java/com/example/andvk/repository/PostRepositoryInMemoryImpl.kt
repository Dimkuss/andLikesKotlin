package com.example.andvk.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.andvk.dto.Post

//import kotlinx.android.synthetic.main.activity_main.*

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Netologiya Universitet internet professiy budushego",
        content = "Привет это Нетология, к сожалению я не нашел полного текста в ресурсах ДЗ, а с экрана переписывать мне не хоче" +
                "тся, по этому я тут просто напишу много слов и думаю, что этого будет достаточно, мои дела отлично, в конце лета двигаюсь в СПБ к другу, буду готовиться" +
                " к собеседованиям на работу, ах точно, вот тут еще сайт, посетите google.com",
        published = "30 July at 16:30",
        likeCount = 9998,
        sharesCount = 1,
        likedByMe = false


    )

    private val data = MutableLiveData(post)

    override fun getAll(): LiveData<Post> = data

    override fun likeById() {

        post = post.copy(
            likedByMe = !post.likedByMe,
            likeCount = if (post.likedByMe) post.likeCount - 1 else post.likeCount + 1
        )
        data.value = post

    }

    override fun share() {
        post = post.copy(sharesCount = post.sharesCount +1)
    }

}