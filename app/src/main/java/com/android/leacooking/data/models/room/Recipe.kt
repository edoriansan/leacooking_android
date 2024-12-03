package com.android.leacooking.data.models.room

import androidx.room.*

@Entity(
    tableName = "recipe",
    foreignKeys = [
        ForeignKey(
            entity = SubCategory::class,
            parentColumns = ["id"],
            childColumns = ["id_subcategory"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "persons")
    val persons: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,

    @ColumnInfo(name = "id_subcategory")
    val subCategoryId: Long,

    val subCategoryLabel: String // temporary
)
