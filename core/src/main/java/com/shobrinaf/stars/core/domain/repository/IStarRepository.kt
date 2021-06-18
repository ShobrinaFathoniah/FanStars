package com.shobrinaf.stars.core.domain.repository

import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.domain.model.Stars
import kotlinx.coroutines.flow.Flow

interface IStarRepository {
    //APOD 2021
    fun getAllStars(): Flow<Resource<List<Stars>>>
    fun getFavoriteStars(): Flow<List<Stars>>
    fun setFavoriteStars(star: Stars, state: Boolean)

    //Search APOD
    fun getSearch(query: String): Flow<Resource<List<Stars>>>
}