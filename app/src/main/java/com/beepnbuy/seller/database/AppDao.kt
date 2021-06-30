package com.beepnbuy.seller.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beepnbuy.seller.data.AppEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mayur Solanki on 25/06/21, 5:23 pm.
 */

@Dao
interface AppDao {

    @Query("SELECT * FROM restaurants")
    fun getAll() : Flow<List<AppEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurants : List<AppEntity>)

    @Query("DELETE from restaurants")
    suspend fun deleteAll()
}