package com.example.andvk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.andvk.*
import com.example.andvk.databinding.CardPostBinding
import com.example.andvk.dto.CounterFormatter
import com.example.andvk.dto.Post


interface OnInteractionListener {
    fun onRemove(post: Post) {}
    fun onEdit(post: Post) {}
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onDiscard(post: Post) {}
    fun onDetailsClicked(post: Post){}
}


class PostAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.isChecked = post.likedByMe
            likes.text = CounterFormatter.formatCounter(post.likeCount)
            shares.text = CounterFormatter.formatCounter(post.sharesCount)

            root.setOnClickListener {
                onInteractionListener.onDetailsClicked(post)
            }
            likes.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            shares.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            popupMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menuActionRemove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.menuActionEdit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }

    }


}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Post, newItem: Post): Any = Unit

}

