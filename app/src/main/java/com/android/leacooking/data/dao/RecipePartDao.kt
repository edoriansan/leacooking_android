package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.room.RecipeCategory
import com.android.leacooking.data.model.room.RecipePart

@Dao
interface RecipePartDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeParts: List<RecipePart>)
}
