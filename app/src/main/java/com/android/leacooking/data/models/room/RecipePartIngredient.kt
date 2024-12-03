package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe_part_ingredient",
    primaryKeys = ["id_recipe_part", "id_ingredient"], // Clé composite
    foreignKeys = [
        ForeignKey(
            entity = RecipePart::class,
            parentColumns = ["id"],
            childColumns = ["id_recipe_part"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["id_ingredient"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_recipe_part"]), Index(value = ["id_ingredient"])]
)
data class RecipePartIngredient(
    @ColumnInfo(name = "id_recipe_part")
    val recipePartId: Long,

    @ColumnInfo(name = "id_ingredient")
    val ingredientId: Long,

    @ColumnInfo(name = "quantity")
    val quantity: String,

    @ColumnInfo(name = "unit")
    val unit: String? = null
)
