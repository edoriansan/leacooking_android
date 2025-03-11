package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.leacooking.data.model.room.QuantityType

@Dao
interface QuantityTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quantityTypes: List<QuantityType>)

    @Query("SELECT count(*) FROM quantity_type")
    suspend fun countQuantityTypes(): Int

    @Query("SELECT * FROM quantity_type")
    fun getAllQuantityTypes(): List<QuantityType>
}
