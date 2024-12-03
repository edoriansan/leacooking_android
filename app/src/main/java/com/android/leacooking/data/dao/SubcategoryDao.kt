package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.models.room.SubCategory

@Dao
interface SubcategoryDao {
    @Transaction
    @Query("SELECT * FROM subcategory WHERE id_category = :categoryId")
    fun getSubcatergoriesByCategoryId(categoryId: Long): LiveData<List<SubCategory>>
}
