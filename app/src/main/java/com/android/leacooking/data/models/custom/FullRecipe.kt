package com.android.leacooking.data.models.custom

import androidx.room.Embedded
import androidx.room.Relation
import com.android.leacooking.data.models.room.Recipe
import com.android.leacooking.data.models.room.RecipePart

data class FullRecipe(
    @Embedded val recipe: Recipe,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_recipe",
        entity = RecipePart::class
    )
    val parts: List<RecipePartWithIngredients>

)
