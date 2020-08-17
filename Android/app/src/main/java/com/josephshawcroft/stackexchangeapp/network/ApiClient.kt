package com.josephshawcroft.stackexchangeapp.network

import com.josephshawcroft.stackexchangeapp.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class ApiClient @Inject constructor(private val stackExchangeService: StackExchangeService) {

    fun fetchUsersByName(name: String) : Single<List<User>> = stackExchangeService.fetchUsersByName(name = name)
}