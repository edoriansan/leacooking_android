package com.android.leacooking.data.model.custom

import androidx.room.Embedded
import androidx.room.Relation
import com.android.leacooking.data.model.room.Recipe

data class FullRecipe(
    @Embedded val recipe: Recipe,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_recipe"
    )
    val ingredients: List<IngredientWithQuantity>
)
