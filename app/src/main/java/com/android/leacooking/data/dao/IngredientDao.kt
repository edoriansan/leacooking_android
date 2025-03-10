package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.room.Ingredient
import com.android.leacooking.data.model.room.QuantityType

@Dao
interface IngredientDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredients: List<Ingredient>)
}
