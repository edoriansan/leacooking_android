package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.leacooking.data.models.room.Ingredient

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredient WHERE id = :id")
    suspend fun getIngredientById(id: Long): Ingredient

    @Query("SELECT * FROM ingredient")
    fun getAllIngredients(): LiveData<List<Ingredient>>
}
