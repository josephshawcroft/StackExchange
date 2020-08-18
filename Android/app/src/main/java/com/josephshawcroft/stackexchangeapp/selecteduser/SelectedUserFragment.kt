package com.josephshawcroft.stackexchangeapp.selecteduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.request.LoadRequest
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.R
import com.josephshawcroft.stackexchangeapp.data.model.User
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

        user?.let { displayUserInfo(it) }

        return binding.root
    }

    private fun displayUserInfo(user: User) {

        val imageLoader = ImageLoader(binding.root.context)
        val request = LoadRequest.Builder(binding.root.context)
            .data(user.profileImage)
            .target(binding.userProfilePic)
            .error(R.drawable.ic_person_black_18dp)
            .build()

        imageLoader.execute(request)

        binding.userNameAnswer.text = user.displayName
        binding.reputationAnswer.text = user.reputation.toString()

        binding.badgesBronze.text = String.format(getString(R.string.bronze), user.badgeCounts.bronze)
        binding.badgesSilver.text = String.format(getString(R.string.silver), user.badgeCounts.silver)
        binding.badgesGold.text = String.format(getString(R.string.gold), user.badgeCounts.gold)

        binding.locationAnswer.text = user.location ?: getString(R.string.not_specified)
        binding.ageAnswer.text = String.format(getString(R.string.days), user.ageOfAccountInDays)
        binding.creationDateAnswer.text = user.formattedCreationDate
    }
}