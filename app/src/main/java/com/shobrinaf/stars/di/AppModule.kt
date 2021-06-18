package com.shobrinaf.stars.di

import com.shobrinaf.stars.core.domain.usecase.StarInteractor
import com.shobrinaf.stars.core.domain.usecase.StarUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideTourismUseCase(starInteractor: StarInteractor): StarUseCase

}
