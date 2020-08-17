package com.josephshawcroft.stackexchangeapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BadgeCounts(
    val bronze: Int,
    val gold: Int,
    val silver: Int
) : Parcelable