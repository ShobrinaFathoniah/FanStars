package com.shobrinaf.stars.core.domain.usecase

import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.domain.model.Stars
import kotlinx.coroutines.flow.Flow

interface StarUseCase {
    //APOD 2021
    fun getAllStars(): Flow<Resource<List<Stars>>>
    fun getFavoriteStar(): Flow<List<Stars>>
    fun setFavoriteStar(star: Stars, state: Boolean)

    //SearchAPOD
    fun getSearch(query: String): Flow<Resource<List<Stars>>>
}