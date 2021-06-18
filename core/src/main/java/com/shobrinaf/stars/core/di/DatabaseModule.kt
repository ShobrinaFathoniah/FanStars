package com.shobrinaf.stars.core.di

import android.content.Context
import androidx.room.Room
import com.shobrinaf.stars.core.data.source.local.room.StarDao
import com.shobrinaf.stars.core.data.source.local.room.StarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): StarDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("shobrinaf".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            StarDatabase::class.java, "Stars.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideStarsDao(database: StarDatabase): StarDao = database.starDao()
}