package com.josephshawcroft.stackexchangeapp.network

import com.josephshawcroft.stackexchangeapp.data.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeService {
    @GET("users?")
    fun fetchUsersByName(
        @Query("order") order: String = "asc",
        @Query("sort") sort: String = "name",
        @Query("inname") name: String,
        @Query("site") site: String = "stackoverflow",
        @Query("max") max: Int = 20
    ): Single<UserResponse>
}