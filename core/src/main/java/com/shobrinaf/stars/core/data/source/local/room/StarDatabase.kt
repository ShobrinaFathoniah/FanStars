package com.shobrinaf.stars.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shobrinaf.stars.core.data.source.local.entity.SearchStarEntity
import com.shobrinaf.stars.core.data.source.local.entity.StarEntity

@Database(
    entities = [StarEntity::class, SearchStarEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StarDatabase : RoomDatabase() {

    abstract fun starDao(): StarDao

}