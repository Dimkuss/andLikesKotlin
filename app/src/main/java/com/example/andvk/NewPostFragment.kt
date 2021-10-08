package com.example.andvk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andvk.databinding.FragmentNewPostBinding
import com.example.andvk.util.AndroidUtils
import com.example.andvk.viewmodel.PostViewModel
import navigation.LongDelegate

class NewPostFragment : Fragment() {
    companion object{
        var Bundle.idArgument : Long? by LongDelegate
    }

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)


        binding.edit.requestFocus()

        val id = arguments?.idArgument

        binding.ok.setOnClickListener {
            val text = binding.edit.text?.toString()
            if (!text.isNullOrBlank()) {
                val content = binding.edit.text.toString()
                viewModel.changeContent(content)
                viewModel.save()
            }
            AndroidUtils.hideKeyboard(binding.root)
            findNavController().navigateUp()
        }
        return binding.root
    }
}