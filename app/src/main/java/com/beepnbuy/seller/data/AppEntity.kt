package com.beepnbuy.seller.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mayur Solanki on 28/06/21, 6:02 pm.
 */
@Entity(tableName = "restaurants")
data class AppEntity(@PrimaryKey val name: String,
                     val type: String,
                     val logo: String,
                     val address: String
)

