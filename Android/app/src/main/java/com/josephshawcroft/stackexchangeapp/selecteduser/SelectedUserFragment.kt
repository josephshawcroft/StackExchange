package com.josephshawcroft.stackexchangeapp.selecteduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.databinding.FragmentSelectedUserBinding
import com.josephshawcroft.stackexchangeapp.databinding.FragmentUserListBinding
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

        binding.userProfilePic.load(user?.profileImage)

        return binding.root
    }
}