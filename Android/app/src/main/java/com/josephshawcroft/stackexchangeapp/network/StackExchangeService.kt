package com.josephshawcroft.stackexchangeapp.network

import com.josephshawcroft.stackexchangeapp.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeService {
    @GET("/2.2/users?")
    fun fetchUsersByName(
        @Query("order") order: String = "asc",
        @Query("sort") sort: String = "name",
        @Query("inname") name: String,
        @Query("site") site: String = "stackoverflow"
    ): Single<List<User>>
}