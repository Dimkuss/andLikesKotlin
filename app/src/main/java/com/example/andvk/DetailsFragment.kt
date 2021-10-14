package com.example.andvk

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import com.example.andvk.EditPostFragment.Companion.postArgument
import com.example.andvk.NewPostFragment.Companion.idArgument
import com.example.andvk.databinding.CardPostBinding
import com.example.andvk.dto.CounterFormatter
import com.example.andvk.util.LongDelegate
import com.example.andvk.viewmodel.PostViewModel

class DetailsFragment : Fragment() {

    companion object {
        var Bundle.idArgument: Long? by LongDelegate
    }

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CardPostBinding.inflate(inflater, container, false)

        val id = requireNotNull(arguments?.idArgument)

        viewModel.data.map { posts ->
            posts.find { it.id == id }
        }.observe(viewLifecycleOwner) {
            val post = it ?: run {
                findNavController().navigateUp()
                return@observe
            }

            binding.apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likes.isChecked = post.likedByMe
                likes.text = CounterFormatter.formatCounter(post.likeCount)
                shares.text = CounterFormatter.formatCounter(post.sharesCount)

                likes.setOnClickListener {
                    viewModel.likeById(post.id)
                }

                shares.setOnClickListener {
                    viewModel.shareById(post.id)

                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, post.content)
                        .let { intent ->
                            Intent.createChooser(intent, null)
                        }
                    if (intent.resolveActivity(requireContext().packageManager) != null) {
                        startActivity(intent)
                    } else {
                        showToast(R.string.app_not_found_error)
                    }
                }
                author.setOnClickListener{
                    findNavController().navigate(R.id.action_feedFragment_to_detailsFragment,
                        Bundle().apply
                        { idArgument = 1L })
                }
                published.setOnClickListener{
                    findNavController().navigate(R.id.action_feedFragment_to_detailsFragment,
                        Bundle().apply
                        { idArgument = 1L })
                }
                content.setOnClickListener{
                    findNavController().navigate(R.id.action_feedFragment_to_detailsFragment,
                        Bundle().apply
                        { idArgument = 1L })
                }

                popupMenu.setOnClickListener { view ->
                    PopupMenu(view.context, view).apply {
                        inflate(R.menu.options_post)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.menuActionRemove -> {
                                    viewModel.removeById(post.id)
                                    true
                                }
                                R.id.menuActionEdit -> {
                                    viewModel.edit(post)
                                    findNavController().navigate(R.id.action_feedFragment_to_editFragment,
                                        Bundle().apply { postArgument = post })
                                    true
                                }
                                else -> false
                            }
                        }
                    }.show()
                }
            }
        }

        return binding.root
    }
}