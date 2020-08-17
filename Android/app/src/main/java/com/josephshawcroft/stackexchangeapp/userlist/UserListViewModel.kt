package com.josephshawcroft.stackexchangeapp.userlist

import androidx.lifecycle.*
import com.josephshawcroft.stackexchangeapp.data.model.User

interface UserListViewModel {

    val users: LiveData<List<User>>

    companion object{
        fun get(owner: ViewModelStoreOwner) : UserListViewModel = ViewModelProvider(owner).get(UserListViewModelImpl::class.java)
    }
}

internal class UserListViewModelImpl : ViewModel(), UserListViewModel {

    override val users: LiveData<List<User>>
        get() = MutableLiveData() //TODO add users via repo
}