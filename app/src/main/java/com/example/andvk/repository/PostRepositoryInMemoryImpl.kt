package com.example.andvk.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.andvk.dto.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryInMemoryImpl(context: Context) : PostRepository {
    private companion object {
        const val POST_FILE = "posts.json"
    }

    private var nextId = 1L
    private val file = context.filesDir.resolve(POST_FILE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val gson = Gson()

    private var posts: List<Post> = file.exists().let { exists ->

        if (exists) {
            gson.fromJson(file.readText(), type)
        } else {
            emptyList()
        }
    }
        set(value) {
            field = value
            sync()
        }


    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likeCount = if (it.likedByMe) it.likeCount - 1 else it.likeCount + 1,
            )
        }

        data.value = posts

    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharesCount = it.sharesCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = posts.firstOrNull()?.id?.inc() ?: 1L,
                    author = "Me",
                    likedByMe = false,
                    published = "Just now"

                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun discardEdit(post: Post) {
        if (post.id == 0L) {
            data.value = posts
            return
        }

        data.value = posts

    }

    private fun sync() {
        file.writeText(gson.toJson(posts))
    }
}


