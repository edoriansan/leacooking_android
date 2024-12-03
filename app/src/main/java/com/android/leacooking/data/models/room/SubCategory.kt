package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "subcategory",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["id_category"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_category"])] // index pour optimiser les requêtes
)
data class SubCategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "sub_category_label")
    val subCategoryLabel: String,

    @ColumnInfo(name = "sub_category_img")
    val subCategoryImg: String,

    @ColumnInfo(name = "id_category")
    val categoryId: Int,

    val categoryLabel: String // temporary
)
