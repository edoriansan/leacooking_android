package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.RecipeSubcategory
import com.android.leacooking.network.dto.RecipeSubcategoryDto

object RecipeSubcategoryMapper {
    fun mapTo(dto: RecipeSubcategoryDto): RecipeSubcategory {
        return RecipeSubcategory(
            id = dto.id,
            recipeSubcategoryLabel = dto.recipeSubcategoryLabel,
            recipeSubcategoryImg = dto.recipeSubcategoryImg,
            recipeCategoryId = dto.recipeCategoryId
        )
    }

    fun mapToEntities(dtos: List<RecipeSubcategoryDto>): List<RecipeSubcategory> {
        return dtos.map { mapTo(it) }
    }
}
