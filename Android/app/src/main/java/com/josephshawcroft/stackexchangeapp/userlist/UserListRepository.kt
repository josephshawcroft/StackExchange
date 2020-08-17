package com.josephshawcroft.stackexchangeapp.userlist

import com.josephshawcroft.stackexchangeapp.data.model.UserResponse
import com.josephshawcroft.stackexchangeapp.network.ApiClient
import io.reactivex.Single
import javax.inject.Inject

interface IUserListRepository {
    fun fetchUsersByName(name: String) : Single<UserResponse>
}

internal class UserListRepository @Inject constructor(private val apiClient: ApiClient) : IUserListRepository {

    override fun fetchUsersByName(name: String): Single<UserResponse> = apiClient.fetchUsersByName(name)
}