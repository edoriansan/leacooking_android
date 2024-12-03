package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.models.room.Category

@Dao
interface CategoryDao {
    @Transaction
    @Query("SELECT * FROM category")
    fun getCategories(): LiveData<List<Category>>
}
