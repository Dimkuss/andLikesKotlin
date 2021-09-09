package com.example.andvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.andvk.adapter.PostAdapter
//import com.example.andvk.adapter.PostAdapter
import com.example.andvk.databinding.ActivityMainBinding
import com.example.andvk.dto.Post
import com.example.andvk.repository.PostRepositoryInMemoryImpl
import com.example.andvk.viewmodel.PostViewModel

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.card_post.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
//

        val adapter = PostAdapter(
            onLikeListener = {
                viewModel.likeById(it.id)
            },
            onShareListener = {
                viewModel.shareById(it.id)
            }
        )




        binding.recList.adapter = adapter



        viewModel.data.observe(this@MainActivity) { post ->

            adapter.submitList(post)
        }
    }
}













