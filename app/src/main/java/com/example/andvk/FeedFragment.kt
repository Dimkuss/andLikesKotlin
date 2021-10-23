package com.example.andvk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andvk.EditPostFragment.Companion.postArgument
import com.example.andvk.NewPostFragment.Companion.idArgument
import com.example.andvk.adapter.OnInteractionListener
import com.example.andvk.adapter.PostAdapter
import com.example.andvk.databinding.FragmentFeedBinding
import com.example.andvk.dto.Post
import com.example.andvk.viewmodel.PostViewModel


class FeedFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                findNavController().navigate(R.id.action_feedFragment_to_editFragment,
                    Bundle().apply { postArgument = post })

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
                if (intent.resolveActivity(requireContext().packageManager) != null) {
                    startActivity(intent)
                } else {
                    showToast(R.string.app_not_found_error)
                }
            }

            override fun onDiscard(post: Post) {
                viewModel.discardChanges()
            }

            override fun onDetailsClicked(post: Post) {
                findNavController().navigate(R.id.action_feedFragment_to_detailsFragment,
                    Bundle().apply { idArgument = post.id })
            }


        })

        binding.recList.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { post ->

            adapter.submitList(post)
        }


        viewModel.edited.observe(viewLifecycleOwner, { post ->
            if (post.id == 0L) {
                return@observe
            }


        })



        binding.addPost.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment,
                Bundle().apply { idArgument = 1L })

        }

        return binding.root
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

fun Fragment.showToast(text: Int, length: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(text, length)
}












