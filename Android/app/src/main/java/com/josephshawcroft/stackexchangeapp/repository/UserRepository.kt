package com.josephshawcroft.stackexchangeapp.repository

import com.josephshawcroft.stackexchangeapp.data.model.User
import com.josephshawcroft.stackexchangeapp.network.ApiClient
import io.reactivex.Single
import javax.inject.Inject

interface IUserRepository {
    fun fetchUsersByName(name: String): Single<List<User>>
}

internal class UserRepository @Inject constructor(private val apiClient: ApiClient) :
    IUserRepository {

    override fun fetchUsersByName(name: String): Single<List<User>> =
        apiClient.fetchUsersByName(name).map { it.users }
}