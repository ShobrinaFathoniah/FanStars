package com.shobrinaf.stars.di

import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun starUseCase(): StarUseCase
}