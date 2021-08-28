//package com.example.andvk.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.andvk.R
//import com.example.andvk.databinding.CardPostBinding
//import com.example.andvk.dto.Post
//
//typealias OnLikeListener = (post: Post) -> Unit
//
//class PostAdapter(private val onLikeListener: OnLikeListener) : RecyclerView.Adapter<PostViewHolder>() {
//    var postsList = emptyList<Post>()
//        set(value){
//            field = value
//            notifyDataSetChanged()
//        }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
//        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PostViewHolder(binding,onLikeListener)
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val post = postsList[position]
//        holder.bind(post)
//    }
//
//    override fun getItemCount(): Int = postsList.size
//
//}
//
//class PostViewHolder(
//    private val binding: CardPostBinding,
//    private val onLikeListener: OnLikeListener,
//) : RecyclerView.ViewHolder() {
//    fun bind(post: Post) {
//        binding.apply {
//            author.text = post.author
//            published.text = post.published
//            content.text = post.content
//            likes.setImageResource(
//                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
//            )
//            likes.setOnClickListener{
//                onLikeListener(post)
//            }
//
//
//        }
//
//    }
//
//}