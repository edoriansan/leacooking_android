package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.RecipeIngredient
import com.android.leacooking.network.dto.RecipeIngredientDto

object RecipeIngredientMapper {
    fun mapTo(dto: RecipeIngredientDto): RecipeIngredient {
        return RecipeIngredient(
            recipeId = dto.recipeId,
            ingredientId = dto.ingredientId,
            quantity = dto.quantity
        )
    }

    fun mapToEntities(dtos: List<RecipeIngredientDto>): List<RecipeIngredient> {
        return dtos.map { mapTo(it) }
    }
}
