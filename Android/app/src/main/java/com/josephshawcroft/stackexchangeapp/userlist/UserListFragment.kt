package com.josephshawcroft.stackexchangeapp.userlist

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.data.model.User
import com.josephshawcroft.stackexchangeapp.databinding.FragmentUserListBinding
import com.josephshawcroft.stackexchangeapp.util.BackPressHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>(), BackPressHandler {

    private lateinit var viewModel : UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = UserListViewModel.get(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(inflater, container, false)
        setBinding(binding)

        binding.userList.adapter = null //TODO add adapter class

        viewModel.users.observe(viewLifecycleOwner, onUserListUpdatedObserver)

        return binding.root
    }

    private val onUserListUpdatedObserver = Observer<List<User>> { users ->
        // todo update adapter with new users here
    }

    override fun onBackPressed(parentActivity: Activity) {
        parentActivity.moveTaskToBack(true)
    }
}
