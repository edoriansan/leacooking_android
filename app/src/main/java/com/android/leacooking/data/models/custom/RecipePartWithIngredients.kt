package com.android.leacooking.data.models.custom

import androidx.room.Embedded
import androidx.room.Relation
import com.android.leacooking.data.models.room.RecipePart
import com.android.leacooking.data.models.room.RecipePartIngredient

data class RecipePartWithIngredients(
    @Embedded val part: RecipePart,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_recipe_part"
    )
    val ingredients: List<RecipePartIngredient>
)
