package com.example.andvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.andvk.adapter.OnInteractionListener
import com.example.andvk.adapter.PostAdapter
import com.example.andvk.databinding.ActivityMainBinding
import com.example.andvk.dto.Post
import com.example.andvk.util.AndroidUtils
import com.example.andvk.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()


        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onDiscard(post: Post) {
                viewModel.discardChanges()
            }
        }


        )

        binding.recList.adapter = adapter

        viewModel.data.observe(this@MainActivity) { post ->

            adapter.submitList(post)
        }


        viewModel.edited.observe(this, { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                requestFocus()
                setText(post.content)
            }

        })
        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Content can't be empty", Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.discardEdit.isGone = true

            }
        }
        binding.content.setOnFocusChangeListener { _, hasFocus ->
            binding.discardEdit.isVisible = hasFocus
        }
        binding.discardEdit.setOnClickListener {
            binding.discardEdit.isGone = true
            with(binding.content) {
                viewModel.discardChanges()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }


        }
    }
}













