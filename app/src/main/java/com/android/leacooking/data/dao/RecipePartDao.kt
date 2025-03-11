package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.leacooking.data.model.room.RecipePart

@Dao
interface RecipePartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeParts: List<RecipePart>)

    @Query("SELECT count(*) FROM recipe_part")
    suspend fun countRecipeParts(): Int

    @Query("SELECT * FROM recipe_part")
    fun getAllRecipeParts(): List<RecipePart>
}
