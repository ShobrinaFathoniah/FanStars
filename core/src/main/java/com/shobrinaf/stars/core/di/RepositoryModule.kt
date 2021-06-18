package com.shobrinaf.stars.core.di

import com.shobrinaf.stars.core.data.StarRepository
import com.shobrinaf.stars.core.domain.repository.IStarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(starRepository: StarRepository): IStarRepository
}