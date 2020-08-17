package com.josephshawcroft.stackexchangeapp.network

import com.josephshawcroft.stackexchangeapp.data.model.UserResponse
import io.reactivex.Single
import javax.inject.Inject

class ApiClient @Inject constructor(private val stackExchangeService: StackExchangeService) {

    fun fetchUsersByName(name: String) : Single<UserResponse> = stackExchangeService.fetchUsersByName(name = name)
}