package com.example.andvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
        with(binding) {
            likes.setOnClickListener {
                viewModel.likeById()
            }
            shares.setOnClickListener {
                viewModel.share()
            }

//        val adapter = PostAdapter{
//            viewModel.like()
//        }
//        binding.recList.adapter = adapter

            viewModel.data.observe(this@MainActivity) { post ->
                textShares.text = formatCounter(post.sharesCount)
                textLikes.text = formatCounter(post.likeCount)
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
            }
        }
    }


    private fun formatCounter(counter: Long): String =
        when (counter) {
            in 1000..1099 -> "${counter / 1000}K"
            in 1100..9999 -> "${counter.toDouble() / 1000}K"
            in 10000..999_999 -> "${counter.toDouble() / 1000}K"
            in 1_000_000..1_099_999 -> "${counter / 1000000}M"
            in 1_100_000..999_999_999 -> "${counter.toDouble() / 1000000}M"
            else -> counter.toString()
        }
}









