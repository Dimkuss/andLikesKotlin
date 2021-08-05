package com.example.andvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.example.andvk.databinding.ActivityMainBinding
import com.example.andvk.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val post = Post(
            id = 1,
            author = "Netologiya Universitet internet professiy budushego",
            content = "Привет это Нетология, к сожалению я не нашел полного текста в ресурсах ДЗ, а с экрана переписывать мне не хоче" +
                    "тся, по этому я тут просто напишу много слов и думаю, что этого будет достаточно, мои дела отлично, в конце лета двигаюсь в СПБ к другу, буду готовиться" +
                    " к собеседованиям на работу, ах точно, вот тут еще сайт, посетите google.com",
            published = "30 July at 16:30",
            likeCount = "999",
            likedByMe = false

        )
        with(binding) {
            this
            textLikes.text = post.likeCount
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                likes?.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            root.setOnClickListener {
                it
                Log.d("stuff", "stuff")
            }
            avatar.setOnClickListener {
                it
                Log.d("stuff", "stuff")
            }
            likes?.setOnClickListener {
                it
                post.likedByMe = !post.likedByMe
                Log.d("LIKE", "LIKE")
                post.likeCount=post.likeCount+1
                likes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                323232
            }
        }

    }


}
