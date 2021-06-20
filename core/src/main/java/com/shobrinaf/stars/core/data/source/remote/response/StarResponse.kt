package com.shobrinaf.stars.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StarResponse(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("copyright")
    val copyright: String,

    @field:SerializedName("media_type")
    val mediaType: String,

    @field:SerializedName("apod_site")
    val apodSite: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("hdurl")
    val hdurl: String,

    @field:SerializedName("url")
    val url: String
)
