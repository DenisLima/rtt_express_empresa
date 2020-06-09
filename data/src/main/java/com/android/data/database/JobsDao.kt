package com.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.android.data.models.AndroidJobCache
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {
    @Query("SELECT * FROM jobs")
    fun getJobs(): Flow<List<AndroidJobCache>>

    @Transaction
    fun updateData(users: List<AndroidJobCache>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<AndroidJobCache>)

    @Query("DELETE FROM jobs")
    fun deleteAll()

    @Insert
    fun insert(vararg job: AndroidJobCache)
}