package com.example.andvk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.andvk.dto.Post
import com.example.andvk.repository.PostRepository
import com.example.andvk.repository.PostRepositoryInMemoryImpl
private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = ""
)
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun edit(post: Post){
        edited.value = post
    }

    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun changeContent(content:String){
        edited.value?.let {
            val text = content.trim()
            if (it.content == text){
                return
            }
            edited.value = it.copy(content = text)
        }
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id:Long) = repository.removeById(id)
}