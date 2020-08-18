package com.josephshawcroft.stackexchangeapp.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.josephshawcroft.stackexchangeapp.BaseFragment
import com.josephshawcroft.stackexchangeapp.data.Response
import com.josephshawcroft.stackexchangeapp.data.model.User
import com.josephshawcroft.stackexchangeapp.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : BaseFragment<FragmentUserListBinding>(), UserListAdapterListener {

    private lateinit var viewModel: UserListViewModel

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

        binding.userList.adapter = UserListAdapter(this)

        binding.searchButton.setOnClickListener { searchForUser() }

        binding.searchText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchForUser()
                    true
                }
                else -> false
            }
        }

        viewModel.users.observe(viewLifecycleOwner, onUserListUpdatedObserver)

        return binding.root
    }

    private fun searchForUser() {
        val searchText = binding.searchText.text.toString()
        viewModel.fetchUsersByName(searchText)
    }

    private val onUserListUpdatedObserver = Observer<Response<List<User>>> { response ->
        when (response) {
            is Response.Success -> {
                binding.searchButton.isEnabled = true
                binding.searchText.isEnabled = true
                binding.userList.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                binding.errorText.visibility = View.INVISIBLE

                (binding.userList.adapter as UserListAdapter).updateUserList(response.data)
            }
            is Response.Error -> {
                binding.searchButton.isEnabled = true
                binding.searchText.isEnabled = true
                binding.userList.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                binding.errorText.visibility = View.VISIBLE
            }
            is Response.IsLoading -> {
                binding.searchButton.isEnabled = false
                binding.searchText.isEnabled = false
                binding.userList.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.errorText.visibility = View.INVISIBLE
            }
        }
    }

    override fun onUserSelected(user: User) {
        val action = UserListFragmentDirections.actionUserListFragmentToSelectedUserFragment(user)
        Navigation.findNavController(binding.root).navigate(action)
    }
}