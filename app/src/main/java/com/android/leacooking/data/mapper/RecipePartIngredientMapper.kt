package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.RecipePartIngredient
import com.android.leacooking.network.dto.RecipePartIngredientDto

object RecipePartIngredientMapper {
    fun mapTo(dto: RecipePartIngredientDto): RecipePartIngredient {
        return RecipePartIngredient(
            recipePartId = dto.recipePartId,
            ingredientId = dto.ingredientId,
            quantity = dto.quantity
        )
    }

    fun mapToEntities(dtos: List<RecipePartIngredientDto>): List<RecipePartIngredient> {
        return dtos.map { mapTo(it) }
    }
}
