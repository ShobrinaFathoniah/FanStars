package com.shobrinaf.stars.core.domain.usecase

import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.core.domain.repository.IStarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarInteractor @Inject constructor(private val starRepository: IStarRepository) :
    StarUseCase {

    //APOD 2021
    override fun getAllStars(): Flow<Resource<List<Stars>>> = starRepository.getAllStars()

    override fun getFavoriteStar(): Flow<List<Stars>> = starRepository.getFavoriteStars()

    override fun setFavoriteStar(star: Stars, state: Boolean) =
        starRepository.setFavoriteStars(star, state)

    //Search APOD
    override fun getSearch(query: String): Flow<Resource<List<Stars>>> =
        starRepository.getSearch(query)
}