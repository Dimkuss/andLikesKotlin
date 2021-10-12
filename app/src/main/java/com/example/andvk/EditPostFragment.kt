package com.example.andvk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andvk.databinding.FragmentEditPostBinding
import com.example.andvk.dto.Post
import com.example.andvk.util.ParcelableDelegate
import com.example.andvk.viewmodel.PostViewModel


class EditPostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
    companion object {
        var Bundle.postArgument: Post? by ParcelableDelegate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(inflater,container,false)


        val post: Post = requireNotNull(arguments?.postArgument)

        binding.edit.setText(post.content)
        binding.edit.requestFocus()

        binding.ok.setOnClickListener {
            val text = binding.edit.text?.toString()
            if (!text.isNullOrBlank()) {
                viewModel.edit(post.copy(content = text.toString()))
                viewModel.save()
                findNavController().navigateUp()
            }

        }
        return binding.root
    }
}