package com.example.andvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.andvk.databinding.ActivityMainBinding
import com.example.andvk.dto.Post
import com.example.andvk.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharesCount = 0

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this, { post ->
            with(binding) {
                this
                textShares.text = post.sharesCount
                textLikes.text = post.likeCount
                author.text = post.author
                published.text = post.published
                content.text = post.content

                if (post.likedByMe) {
                    likes.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
            }


//            root.setOnClickListener {
//                it
//                Log.d("ROOT", "ROOT")
//            }
            avatar.setOnClickListener {
                it
                Log.d("AVATAR", "AVATAR")
            }
            likes?.setOnClickListener {
                it

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
//                    textLikes.text = "$counter"
                    when (counter) {
                        in 0..999 -> textLikes.text = "$counter"
                        in 1000..1099 -> textLikes.text = "${counter / 1000}K"

                        in 1100..9999 -> textLikes.text = "${counter.toDouble() / 1000}K"
                        in 10000..999_999 -> textLikes.text = "${counter.toDouble() / 1000}K"
                        in 1_000_000..1_099_999 -> textLikes.text = "${counter / 1000000}M"
                        in 1_100_000..999_999_999 -> textLikes.text =
                            "${counter.toDouble() / 1000000}M"

                    }

                } else {
                    counter - 1
//                    textLikes.text = "$counter"
                    when (counter) {
                        in 0..999 -> textLikes.text = "$counter"
                        in 1000..1099 -> textLikes.text = "${counter / 1000}K"
                        in 1100..9999 -> textLikes.text = "${counter.toDouble() / 1000}K"
                        in 10000..999_999 -> textLikes.text = "${counter / 1000}K"
                        in 1_000_000..1_099_999 -> textLikes.text = "${counter / 1000000}M"
                        in 1_100_000..999_999_999 -> textLikes.text =
                            "${counter.toDouble() / 1000000}M"

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

        })


    }


}
