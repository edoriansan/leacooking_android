package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.RecipeCategory
import com.android.leacooking.network.dto.RecipeCategoryDto

object RecipeCategoryMapper {
    fun mapTo(dto: RecipeCategoryDto): RecipeCategory {
        return RecipeCategory(
            id = dto.id,
            recipeCategoryLabel = dto.recipeCategoryLabel,
            recipeCategoryImg = dto.recipeCategoryImg
        )
    }

    fun mapToEntities(dtos: List<RecipeCategoryDto>): List<RecipeCategory> {
        return dtos.map { mapTo(it) }
    }
}
