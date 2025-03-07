package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.models.room.QuantityType
import com.android.leacooking.data.models.room.RecipeCategory

@Dao
interface RecipeCategoryDao {
    @Transaction
    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<RecipeCategory>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeCategories: List<RecipeCategory>)
}
