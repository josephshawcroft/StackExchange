package com.josephshawcroft.stackexchangeapp.selecteduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import coil.transform.CircleCropTransformation
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.R
import com.josephshawcroft.stackexchangeapp.databinding.FragmentSelectedUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedUserFragment : BaseFragment<FragmentSelectedUserBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectedUserBinding.inflate(inflater, container, false)
        setBinding(binding)

        val user = arguments?.let {
            SelectedUserFragmentArgs.fromBundle(it).selectedUser
        }

        val imageLoader = ImageLoader(binding.root.context)
        val request = LoadRequest.Builder(binding.root.context)
            .data(user?.profileImage)
            .target(binding.userProfilePic)
            .error(R.drawable.ic_person_black_18dp)
            .build()

        imageLoader.execute(request)

        return binding.root
    }
}