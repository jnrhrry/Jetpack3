package com.dicoding.jetpack3.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesResponse(
    var tvShowId:String,
    var title: String,
    var release: String,
    var rating: String,
    var description: String,
    var imagePath: String
): Parcelable