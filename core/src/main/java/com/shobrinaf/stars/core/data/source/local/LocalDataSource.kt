package com.shobrinaf.stars.core.data.source.local

import com.shobrinaf.stars.core.data.source.local.entity.SearchStarEntity
import com.shobrinaf.stars.core.data.source.local.entity.StarEntity
import com.shobrinaf.stars.core.data.source.local.room.StarDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val starDao: StarDao) {
    //APOD 2021
    fun getAllStars(): Flow<List<StarEntity>> = starDao.getAllStars()

    fun getFavoriteStar(): Flow<List<StarEntity>> = starDao.getFavoriteStar()

    suspend fun insertStar(starList: List<StarEntity>) = starDao.insertStar(starList)

    fun setFavoriteStar(star: StarEntity, newState: Boolean) {
        star.isFavorite = newState
        starDao.updateFavoriteStar(star)
    }

    //Search APOD
    fun getSearchStars(search: String): Flow<List<SearchStarEntity>> =
        starDao.getSearchStars(search)

    suspend fun insertSearchStar(stars: List<SearchStarEntity>) = starDao.insertSearchStar(stars)

}