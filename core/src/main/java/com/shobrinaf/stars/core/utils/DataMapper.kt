package com.shobrinaf.stars.core.utils

import com.shobrinaf.stars.core.data.source.local.entity.SearchStarEntity
import com.shobrinaf.stars.core.data.source.local.entity.StarEntity
import com.shobrinaf.stars.core.data.source.remote.response.StarResponse
import com.shobrinaf.stars.core.domain.model.Stars

object DataMapper {
    //APOD 2021
    fun mapResponsesToEntities(input: List<StarResponse>): List<StarEntity> {
        val starList = ArrayList<StarEntity>()
        input.map {
            val star = StarEntity(
                it.apodSite ?: "",
                it.date ?: "",
                it.copyright ?: "",
                it.mediaType ?: "",
                it.description ?: "",
                it.title ?: "",
                it.hdurl ?: "",
                it.url ?: "",
                isFavorite = false
            )
            starList.add(star)
        }
        return starList
    }

    fun mapEntitiesToDomain(input: List<StarEntity>): List<Stars> =
        input.map {
            Stars(
                it.apodSite ?: "",
                it.date ?: "",
                it.copyright ?: "",
                it.mediaType ?: "",
                it.description ?: "",
                it.title ?: "",
                it.url ?: "",
                it.hdurl ?: "",
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Stars) = StarEntity(
        input.apodSite ?: "",
        input.date ?: "",
        input.copyright ?: "",
        input.mediaType ?: "",
        input.description ?: "",
        input.title ?: "",
        input.hdurl ?: "",
        input.url ?: "",
        isFavorite = input.isFavorite
    )

    //search
    fun mapSearchResponsesToEntities(input: List<StarResponse>): List<SearchStarEntity> {
        val starList = ArrayList<SearchStarEntity>()
        input.map {
            val star = SearchStarEntity(
                it.apodSite ?: "",
                it.date ?: "",
                it.copyright ?: "",
                it.mediaType ?: "",
                it.description ?: "",
                it.title ?: "",
                it.hdurl ?: "",
                it.url ?: ""
            )
            starList.add(star)
        }
        return starList
    }

    fun mapSearchEntitiesToDomain(input: List<SearchStarEntity>): List<Stars> =
        input.map {
            Stars(
                it.apodSite ?: "",
                it.date ?: "",
                it.copyright ?: "",
                it.mediaType ?: "",
                it.description ?: "",
                it.title ?: "",
                it.url ?: "",
                it.hdurl ?: "",
                false
            )
        }
}