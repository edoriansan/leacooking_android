package com.android.leacooking.data.models.custom

import androidx.room.Embedded
import androidx.room.Relation
import com.android.leacooking.data.models.room.Ingredient
import com.android.leacooking.data.models.room.QuantityType
import com.android.leacooking.data.models.room.RecipePartIngredient

data class IngredientWithQuantity(
    @Embedded val partIngredient: RecipePartIngredient,

    @Relation(
        parentColumn = "id_ingredient",
        entityColumn = "id"
    )
            val ingredient: Ingredient,

    @Relation(
        parentColumn = "ingredient",
        entityColumn = "id",
        entity = QuantityType::class
    )
    val quantityType: QuantityType?
)

