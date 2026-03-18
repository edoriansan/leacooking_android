package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.leacooking.data.model.room.Ingredient

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredients: List<Ingredient>)

    @Query("SELECT count(*) FROM ingredient")
    suspend fun countIngredients(): Int

    @Query("SELECT * FROM ingredient")
    fun getAllIngredients(): List<Ingredient>
}
