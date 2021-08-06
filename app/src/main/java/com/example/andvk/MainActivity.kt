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
            sharesCount = "1",
            likedByMe = false

        )
        with(binding) {
            this
            textShares.text = post.sharesCount
            textLikes.text = post.likeCount
            author.text = post.author
            published.text = post.published
            content.text = post.content
            var sharesCount = 0
            if (post.likedByMe) {
                likes?.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            root.setOnClickListener {
                it
                Log.d("ROOT", "ROOT")
            }
            avatar.setOnClickListener {
                it
                Log.d("AVATAR", "AVATAR")
            }
            likes?.setOnClickListener {
                it
                post.likedByMe = !post.likedByMe
                Log.d("LIKE", "LIKE")
                var counter = post.likeCount.toInt()
//                if(counter in 1000..999_999) {
//                    counter / 1000
//                    textLikes.text = "$counter"+"K"
//                }
//                if(counter in 1_000_000..999_999_999) {
//                    counter / 1000000
//                    textLikes.text = "$counter"+"M"
//                }
                if (post.likedByMe) {
                    counter++
                    textLikes.text = "$counter"
                    when (counter){
                        in 1000..999_999 -> textLikes.text = "${counter.toDouble()/1000}K"
                        in 1_000_000..999_999_999 -> textLikes.text = "${counter.toDouble()/1000000}M"
                    }

                } else {
                    counter - 1
                    textLikes.text = "$counter"
                    when (counter){
                        in 1000..999_999 -> textLikes.text = "${counter.toDouble()/1000}K"
                        in 1_000_000..999_999_999 -> textLikes.text = "${counter.toDouble()/1000000}M"
                    }
                }


                likes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )

            }
            shares?.setOnClickListener {
                it

                ++sharesCount

                textShares.text = "$sharesCount"
            }

    }


}}
