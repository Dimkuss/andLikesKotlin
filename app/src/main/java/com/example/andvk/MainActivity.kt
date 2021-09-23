package com.example.andvk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.andvk.adapter.OnInteractionListener
import com.example.andvk.adapter.PostAdapter
import com.example.andvk.databinding.ActivityMainBinding
import com.example.andvk.dto.Post
import com.example.andvk.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

                val intent = Intent(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, post.content)
                    .let {
                        Intent.createChooser(it, null)
                    }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    showToast(R.string.app_not_found_error)
                }
            }

            override fun onDiscard(post: Post) {
                viewModel.discardChanges()
            }


        })

        binding.recList.adapter = adapter

        viewModel.data.observe(this@MainActivity) { post ->

            adapter.submitList(post)
        }


        viewModel.edited.observe(this, { post ->
            if (post.id == 0L) {
                return@observe
            }


        })
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        binding.addPost.setOnClickListener {
            newPostLauncher.launch()
        }

    }


}

private fun Context.showToast(text: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        getString(text),
        length
    )
        .show()
}













