package com.josephshawcroft.stackexchangeapp.userlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.josephshawcroft.stackexchangeapp.data.Response
import com.josephshawcroft.stackexchangeapp.data.model.User
import io.reactivex.schedulers.Schedulers

interface UserListViewModel {

    val users: LiveData<Response<List<User>>>

    fun fetchUsersByName(name: String)

    companion object {
        fun get(owner: ViewModelStoreOwner): UserListViewModel =
            ViewModelProvider(owner).get(UserListViewModelImpl::class.java)
    }
}

internal class UserListViewModelImpl @ViewModelInject constructor(
    private val repository: IUserListRepository
) : ViewModel(), UserListViewModel {

    private val usersLiveData: MutableLiveData<Response<List<User>>> = MutableLiveData(Response.NotLoaded())

    override val users: LiveData<Response<List<User>>>
        get() = usersLiveData

    override fun fetchUsersByName(name: String) {
        usersLiveData.value = Response.IsLoading()

        repository.fetchUsersByName(name)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
            { users -> usersLiveData.postValue(Response.Success(users)) },
            { error -> usersLiveData.postValue(Response.Error(error)) }
        ).dispose()
    }
}