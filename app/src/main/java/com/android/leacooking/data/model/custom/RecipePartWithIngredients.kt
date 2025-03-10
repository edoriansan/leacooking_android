package com.android.leacooking.data.model.custom

import androidx.room.Embedded
import androidx.room.Relation
import com.android.leacooking.data.model.room.RecipePart

data class RecipePartWithIngredients(
    @Embedded val part: RecipePart,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_recipe_part"
    )
    val ingredients: List<IngredientWithQuantity>
)
