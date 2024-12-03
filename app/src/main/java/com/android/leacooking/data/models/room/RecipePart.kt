package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe_part",
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["id_recipe"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipePart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "id_recipe")
    val recipeId: Long,

    @ColumnInfo(name = "recipe_part_name")
    val recipePartName: String
)
