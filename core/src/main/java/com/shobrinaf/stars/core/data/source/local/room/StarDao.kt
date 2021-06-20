package com.shobrinaf.stars.core.data.source.local.room

import androidx.room.*
import com.shobrinaf.stars.core.data.source.local.entity.SearchStarEntity
import com.shobrinaf.stars.core.data.source.local.entity.StarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarDao {
    //APOD 2021
    @Query("SELECT * FROM stars")
    fun getAllStars(): Flow<List<StarEntity>>

    @Query("SELECT * FROM stars where isFavorite = 1")
    fun getFavoriteStar(): Flow<List<StarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStar(stars: List<StarEntity>)

    @Update
    fun updateFavoriteStar(stars: StarEntity)

    //search APOD
    @Query("SELECT * FROM searchStars where title like :search")
    fun getSearchStars(search: String): Flow<List<SearchStarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchStar(stars: List<SearchStarEntity>)
}
