package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.RecipePart
import com.android.leacooking.network.dto.RecipePartDto

object RecipePartMapper {
    fun mapTo(dto: RecipePartDto): RecipePart {
        return RecipePart(
            id = dto.id,
            recipePartTitle = dto.recipePartTitle,
            recipePartProcess = dto.recipePartProcess,
            recipeId = dto.recipeId
        )
    }

    fun mapToEntities(dtos: List<RecipePartDto>): List<RecipePart> {
        return dtos.map { mapTo(it) }
    }
}
