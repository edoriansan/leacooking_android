package com.android.leacooking.data.mapper

import com.android.leacooking.data.model.room.QuantityType
import com.android.leacooking.network.dto.QuantityTypeDto

object QuantityTypeMapper {
    fun mapTo(dto: QuantityTypeDto): QuantityType {
        return QuantityType(
            id = dto.id,
            label = dto.label
        )
    }

    fun mapToEntities(dtos: List<QuantityTypeDto>): List<QuantityType> {
        return dtos.map { mapTo(it) }
    }
}
