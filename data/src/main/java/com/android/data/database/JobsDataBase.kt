package com.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.data.features.jobslist.models.AndroidJobCache

@Database(version = 1, entities = [AndroidJobCache::class])
abstract class JobsDataBase : RoomDatabase() {
    abstract fun jobsDao(): JobsDao


    companion object {
        fun createDataBase(context: Context) : JobsDao {
            return Room
                .databaseBuilder(context, JobsDataBase::class.java, "Jobs.db")
                .build()
                .jobsDao()
        }
    }
}