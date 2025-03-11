package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.network.dto.RecipeDto

object RecipeMapper {
    fun mapTo(dto: RecipeDto): Recipe {
        return Recipe(
            id = dto.id,
            title = dto.title,
            recipeImg = dto.recipeImg,
            persons = dto.persons,
            recipeSubcategoryId = dto.recipeSubcategoryId
        )
    }

    fun mapToEntities(dtos: List<RecipeDto>): List<Recipe> {
        return dtos.map { mapTo(it) }
    }
}
