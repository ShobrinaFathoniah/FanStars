package com.shobrinaf.stars.core.data

import com.shobrinaf.stars.core.data.source.local.LocalDataSource
import com.shobrinaf.stars.core.data.source.remote.RemoteDataSource
import com.shobrinaf.stars.core.data.source.remote.network.ApiResponse
import com.shobrinaf.stars.core.data.source.remote.response.StarResponse
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.core.domain.repository.IStarRepository
import com.shobrinaf.stars.core.utils.AppExecutors
import com.shobrinaf.stars.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IStarRepository {

    //APOD 2021
    override fun getAllStars(): Flow<Resource<List<Stars>>> =
        object :
            NetworkBoundResource<List<Stars>, List<StarResponse>>() {
            override fun loadFromDB(): Flow<List<Stars>> {
                return localDataSource.getAllStars().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Stars>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<StarResponse>>> =
                remoteDataSource.getAllStars()

            override suspend fun saveCallResult(data: List<StarResponse>) {
                val starList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertStar(starList)
            }
        }.asFlow()

    override fun getFavoriteStars(): Flow<List<Stars>> {
        return localDataSource.getFavoriteStar().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteStars(star: Stars, state: Boolean) {
        val starEntity = DataMapper.mapDomainToEntity(star)
        appExecutors.diskIO().execute { localDataSource.setFavoriteStar(starEntity, state) }
    }

    //Search APOD
    override fun getSearch(query: String): Flow<Resource<List<Stars>>> =
        object :
            NetworkBoundResource<List<Stars>, List<StarResponse>>() {

            override fun shouldFetch(data: List<Stars>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<StarResponse>>> =
                remoteDataSource.getSearch(query)

            override suspend fun saveCallResult(data: List<StarResponse>) {
                val starList = DataMapper.mapSearchResponsesToEntities(data)
                localDataSource.insertSearchStar(starList)
            }

            override fun loadFromDB(): Flow<List<Stars>> {
                return localDataSource.getSearchStars("%%${query}%%").map {
                    DataMapper.mapSearchEntitiesToDomain(it)
                }
            }
        }.asFlow()
}

