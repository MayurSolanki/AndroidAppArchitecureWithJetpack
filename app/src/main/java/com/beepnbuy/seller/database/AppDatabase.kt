package com.beepnbuy.seller.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beepnbuy.seller.data.AppEntity

/**
 * Created by Mayur Solanki on 25/06/21, 5:27 pm.
 */
@Database(entities = [AppEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao():AppDao
}