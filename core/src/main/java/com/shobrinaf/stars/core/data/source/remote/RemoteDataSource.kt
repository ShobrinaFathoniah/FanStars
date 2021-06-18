package com.shobrinaf.stars.core.data.source.remote

import android.util.Log
import com.shobrinaf.stars.core.data.source.remote.network.ApiResponse
import com.shobrinaf.stars.core.data.source.remote.network.ApiService
import com.shobrinaf.stars.core.data.source.remote.response.StarResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllStars(): Flow<ApiResponse<List<StarResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearch(query: String): Flow<ApiResponse<List<StarResponse>>> {
        return flow {
            try {
                val response = apiService.getSearch(query)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

