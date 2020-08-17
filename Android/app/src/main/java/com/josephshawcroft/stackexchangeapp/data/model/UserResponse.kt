package com.josephshawcroft.stackexchangeapp.data.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val users: List<User>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)