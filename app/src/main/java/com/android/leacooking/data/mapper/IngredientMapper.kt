package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.Ingredient
import com.android.leacooking.network.dto.IngredientDto

object IngredientMapper {
    fun mapTo(dto: IngredientDto): Ingredient {
        return Ingredient(
            id = dto.id,
            label = dto.label,
            quantityTypeId = dto.quantityTypeId
        )
    }

    fun mapToEntities(dtos: List<IngredientDto>): List<Ingredient> {
        return dtos.map { mapTo(it) }
    }
}
