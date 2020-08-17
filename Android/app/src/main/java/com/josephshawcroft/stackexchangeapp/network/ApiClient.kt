package com.josephshawcroft.stackexchangeapp.network

import com.josephshawcroft.stackexchangeapp.data.model.UserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ApiClient @Inject constructor(retrofit: Retrofit) {

    private val stackExchangeService : StackExchangeService = retrofit.create(StackExchangeService::class.java)

    fun fetchUsersByName(name: String) : Single<UserResponse> = stackExchangeService.fetchUsersByName(name = name)
}