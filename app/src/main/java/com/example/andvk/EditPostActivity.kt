package com.example.andvk

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andvk.databinding.ActivityEditPostBinding
import com.example.andvk.dto.Post


class EditPostActivity : AppCompatActivity() {
    companion object {
        const val POST_KEY = "post"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post: Post? = intent.getParcelableExtra(POST_KEY)

        binding.edit.setText(post?.content)
        binding.edit.requestFocus()

        binding.ok.setOnClickListener {
            val text = binding.edit.text?.toString()
            if (text.isNullOrBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                val intent = Intent()
                    .putExtra(POST_KEY,post?.copy(content = text))
                setResult(RESULT_OK,intent)
                finish()
            }

        }
    }


}