package com.shobrinaf.stars.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stars(
    val apodSite: String,
    val date: String,
    val copyright: String,
    val mediaType: String,
    val description: String,
    val title: String,
    val url: String,
    val isFavorite: Boolean
) : Parcelable