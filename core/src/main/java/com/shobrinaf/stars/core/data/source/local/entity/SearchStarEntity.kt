package com.shobrinaf.stars.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchStars")
data class SearchStarEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "apodSite")
    var apodSite: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "copyright")
    var copyright: String,

    @ColumnInfo(name = "media_type")
    var mediaType: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String

)